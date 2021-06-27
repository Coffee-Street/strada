package com.wnsgml972.strada.api.item.noncoffee

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.item.noncoffee.controller.admin.NonCoffeeController
import com.wnsgml972.strada.api.v1.item.noncoffee.service.NonCoffeeDTO
import com.wnsgml972.strada.api.v1.item.noncoffee.service.NonCoffeeInsertRequest
import mu.KLogging
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class NonCoffeeControllerIT@Autowired constructor(
    private val client: WebTestClient,
) : IntegrationTest() {
    val authHelper = AuthHelper(client)

    @BeforeAll
    fun `insert dummy date before test`() {
        val nonCoffeeInsertRequest = NonCoffeeInsertRequest("http://NonCoffeeInsertTest.com", 2000, "insert NonCoffee", "NonCoffee")
        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri(NonCoffeeController.NONCOFFEE_BASE_URL + "/dummy")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nonCoffeeInsertRequest)
            .exchange()

    }

    @Test
    @Order(1)
    fun `insert NonCoffee using post to BreadController`() {
        val nonCoffeeInsertRequest = NonCoffeeInsertRequest("http://NonCoffeeInsertTest.com", 2000, "insert NonCoffee", "NonCoffee")
        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri(NonCoffeeController.NONCOFFEE_BASE_URL + "/test")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nonCoffeeInsertRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<NonCoffeeDTO>()
            .consumeWith { result -> logger.debug { "result=${result.responseBody}" } }
    }

    @Test
    @Order(2)
    fun `select NonCoffee using get from NonCoffeeController`() {
        val accessToken = authHelper.getAccessToken()

        client.get()
            .uri(NonCoffeeController.NONCOFFEE_BASE_URL + "/dummy")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<NonCoffeeDTO>()
            .consumeWith { result -> logger.debug { "result=${result.responseBody}" } }
    }

    @Test
    @Order(3)
    fun `select all NonCoffee using get from NonCoffeeController`() {
        val accessToken = authHelper.getAccessToken()
        client.get()
            .uri(NonCoffeeController.NONCOFFEE_BASE_URL + "/")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<List<NonCoffeeDTO>>()
            .consumeWith { result ->
                result.responseBody?.forEach { it -> logger.debug { "result=${it}" } }
            }
    }

    @Test
    @Order(4)
    fun `update NonCoffee using put to NonCoffeeController`() {
        val nonCoffeeInsertRequest = NonCoffeeInsertRequest("http://NonCoffeeInsertTest.com", 10000, "insert NonCoffee", "NonCoffee")
        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri(NonCoffeeController.NONCOFFEE_BASE_URL + "/test")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nonCoffeeInsertRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<NonCoffeeDTO>().consumeWith { result ->
                result.responseBody?.price shouldBeEqualTo 10000
                logger.debug { "result=${result.responseBody}" }
            }

    }

    @Test
    @Order(5)
    fun `delete NonCoffee using delete from NonCoffeeController`() {
        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri(NonCoffeeController.NONCOFFEE_BASE_URL + "/test")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
    }

    @Test
    @Order(6)
    fun `delete Non Exist NonCoffee using delete from NonCoffeeController`() {
        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri(NonCoffeeController.NONCOFFEE_BASE_URL + "/test")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().isNotFound()
    }

    companion object : KLogging()
}


