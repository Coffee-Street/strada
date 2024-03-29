package com.wnsgml972.strada.api.products.coffee

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.products.ProductHelper
import com.wnsgml972.strada.api.v1.product.coffee.controller.CoffeeController
import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeInsertRequest
import mu.KLogging
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@TestMethodOrder(OrderAnnotation::class)
class CoffeeControllerIT @Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
    private val productHelper: ProductHelper
) : IntegrationTest() {

    @BeforeEach
    fun `insert dummy date before each test`() {

        val coffeeInsertRequest = CoffeeInsertRequest(
            "http://coffeeInsertTest.com",
            2000,
            "insert coffee",
            "coffee",
            listOf(
                BeanDTO("dummybean",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test")
            )
        )

        productHelper.insertCoffee("dummy", coffeeInsertRequest)
    }

    @AfterEach
    fun `delete after each test`(){

        productHelper.deleteCoffee("dummy")
        productHelper.deleteBean("dummybean")
    }

    @Test
    @Order(1)
    fun `insert Conffee using post to CoffeeController`() {

        val coffeeInsertRequest = CoffeeInsertRequest(
            "http://coffeeInsertTest.com",
            3000,
            "insert coffee",
            "coffee",
            listOf(
                BeanDTO("케냐",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test")
            )
        )

        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri("${CoffeeController.COFFEE_BASE_URL}/test")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(coffeeInsertRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<CoffeeDTO>()
            .consumeWith { result -> logger.debug { "result=${result.responseBody}" } }

        productHelper.deleteCoffee("test")
        productHelper.deleteBean("케냐")

    }

    @Test
    @Order(2)
    fun `select Coffee using get from CoffeeController`() {

        val accessToken = authHelper.getAccessToken()
        client.get()
            .uri("${CoffeeController.COFFEE_BASE_URL}/dummy")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<CoffeeDTO>()
            .consumeWith {
                    result -> logger.debug { "result=${result.responseBody}" }
            }

    }

    @Test
    @Order(3)
    fun `select all Coffee using get from CoffeeController`() {

        val accessToken = authHelper.getAccessToken()
        client.get()
            .uri("${CoffeeController.COFFEE_BASE_URL}")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<List<CoffeeDTO>>()
            .consumeWith { result ->
                result.responseBody?.forEach { it -> logger.debug { "result=${it}" } }
            }
    }

    @Test
    @Order(4)
    fun `update Coffee using put to CoffeeController`() {

        val coffeeInsertRequest = CoffeeInsertRequest(
            "http://coffeeInsertTest.com",
            10000,
            "insert coffee",
            "coffee",
            listOf(
                BeanDTO("dummybean",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test")
            )
        )

        val accessToken = authHelper.getAccessToken()
        client.put()
            .uri("${CoffeeController.COFFEE_BASE_URL}/dummy")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(coffeeInsertRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<CoffeeDTO>()
            .consumeWith {
                    result -> result.responseBody?.price shouldBeEqualTo 10000
                    logger.debug { "result=${result.responseBody}"
                }
            }

    }

    @Test
    @Order(5)
    fun `delete Coffee using delete from CoffeeController`() {

        val coffeeInsertRequest = CoffeeInsertRequest(
            "http://coffeeInsertTest.com",
            2000,
            "insert coffee",
            "coffee",
            listOf(
                BeanDTO("test_bean",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test",
                    "test")
            )
        )
        productHelper.insertCoffee("test_coffee", coffeeInsertRequest)

        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri("${CoffeeController.COFFEE_BASE_URL}/test_coffee")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful

        productHelper.deleteBean("test_bean")
    }

    @Test
    @Order(6)
    fun `delete Non Exist Coffee using delete from CoffeeController`() {

        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri("${CoffeeController.COFFEE_BASE_URL}/nothing")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().isNotFound
    }

    companion object : KLogging()
}

