package com.wnsgml972.strada.api.products.bread

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.products.ProductHelper
import com.wnsgml972.strada.api.v1.product.bread.controller.admin.BreadController
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.bread.service.BreadInsertRequest
import mu.KLogging
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@TestMethodOrder(OrderAnnotation::class)
class BreadControllerIT @Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
    private val productHelper: ProductHelper
) : IntegrationTest() {

    @BeforeEach
    fun `insert dummy date before each test`() {

        val breadDTO = BreadDTO(
            "dummy",
            "http://breadInsertTest.com",
            2000,
            "insert bread",
            "bread"
        )
        productHelper.insertBread(breadDTO)
    }

    @AfterEach
    fun `delete after each test`(){

        productHelper.clearBread()
    }

    @Test
    @Order(1)
    fun `insert Bread using post to BreadController`() {

        val breadInsertRequest = BreadInsertRequest(
            "http://breadinserttest.com",
            1000,
            "insert test",
            "breads")

        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri("${BreadController.BREAD_BASE_URL}/test_bread")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(breadInsertRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<BreadDTO>()
            .consumeWith { result ->logger.debug { "result=${result.responseBody}" } }
    }

    @Test
    @Order(2)
    fun `select Bread using get from BreadController`() {

        val accessToken = authHelper.getAccessToken()
        client.get()
            .uri("${BreadController.BREAD_BASE_URL}/dummy")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<BreadDTO>()
            .consumeWith { result ->logger.debug { "result=${result.responseBody}" } }
    }

    @Test
    @Order(3)
    fun `select all Bread using get from BreadController`() {

        val accessToken = authHelper.getAccessToken()
        client.get()
            .uri("${BreadController.BREAD_BASE_URL}")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<List<BreadDTO>>()
            .consumeWith { result ->
                result.responseBody?.forEach { it -> logger.debug { "result=${it}" } }
            }
    }

    @Test
    @Order(4)
    fun `update Bread using put to BreadController`() {

        val breadInsertRequest = BreadInsertRequest(
            "http://breadinserttest.com",
            10000,
            "insert test",
            "breads")

        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri("${BreadController.BREAD_BASE_URL}/dummy")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(breadInsertRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<BreadDTO>().consumeWith { result ->
                result.responseBody?.price shouldBeEqualTo 10000
                logger.debug { "result=${result.responseBody}" }
            }

    }

    @Test
    @Order(5)
    fun `delete Bread using delete from BreadController`() {

        val breadDTO = BreadDTO(
            "delete_bread",
            "http://breadInsertTest.com",
            2000,
            "insert bread",
            "bread"
        )
        productHelper.insertBread(breadDTO)

        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri("${BreadController.BREAD_BASE_URL}/delete_bread")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
    }

    @Test
    @Order(6)
    fun `delete Non Exist Bread using delete from BreadController`() {

        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri("${BreadController.BREAD_BASE_URL}/nothing")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().isNotFound()
    }

    companion object : KLogging()
}

