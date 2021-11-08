package com.wnsgml972.strada.api.banner

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.banner.controller.BannerController
import com.wnsgml972.strada.api.v1.banner.service.BannerDTO
import com.wnsgml972.strada.api.v1.banner.service.BannerInsertRequest
import com.wnsgml972.strada.api.v1.banner.service.BannerInsertResponse
import mu.KLogging
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BannerControllerIT@Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
    private val bannerHelper: BannerHelper
) : IntegrationTest() {

    @BeforeEach
    fun `insert dummy date before test`(){

        val bannerInsertRequest = BannerInsertRequest(
            10,
            "dummy title",
            "http://dummy",
            "this is dummy",
            "#ffffff",
            "#000000"
        )
        bannerHelper.insertBanner("dummy", bannerInsertRequest)
    }

    @AfterEach
    fun `clear dummy date after test`(){
        bannerHelper.clearBanner()
    }

    @Test
    @Order(1)
    fun `insert banner using post to BannerController`() {

        val bannerInsertRequest = BannerInsertRequest(
            1,
            "test insert",
            "http://dummy",
            "this is dummy",
            "#ffffff",
            "#000000"
        )

        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri("${BannerController.BANNER_BASE_URL}/test")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(bannerInsertRequest)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody<BannerInsertResponse>()
            .consumeWith { result -> logger.debug { "result=${result.responseBody}" } }
    }

    @Test
    @Order(2)
    fun `select Banner using get from BannerController`() {

        val accessToken = authHelper.getAccessToken()
        client.get()
            .uri("${BannerController.BANNER_BASE_URL}/dummy")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody<BannerDTO>()
            .consumeWith { result ->
                logger.debug { "result=${result.responseBody}" }
            }
    }

    @Test
    @Order(3)
    fun `select all Banner using get from BannerController`() {

        val accessToken = authHelper.getAccessToken()
        client.get()
            .uri("${BannerController.BANNER_BASE_URL}")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody<List<BannerDTO>>()
            .consumeWith { result ->
                result.responseBody?.forEach { it -> logger.debug { "result=${it}" } }
            }
    }

    @Test
    @Order(4)
    fun `update Banner using put to BannerController`() {

        val bannerInsertRequest = BannerInsertRequest(
            1,
            "test update",
            "http://dummy",
            "this is dummy",
            "#ffffff",
            "#000000"
        )

        val accessToken = authHelper.getAccessToken()
        client.put()
            .uri("${BannerController.BANNER_BASE_URL}/dummy")
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(bannerInsertRequest)
            .exchange()
            .expectStatus()
            .is2xxSuccessful
            .expectBody<BannerInsertResponse>()
            .consumeWith { result ->
                logger.debug { "result=${result.responseBody}" }
            }

    }

    @Test
    @Order(5)
    fun `delete Banner using delete from BannerController`() {

        val bannerInsertRequest = BannerInsertRequest(
            10,
            "test delete",
            "http://dummy",
            "this is dummy",
            "#ffffff",
            "#000000"
        )

        bannerHelper.insertBanner("test", bannerInsertRequest)
        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri("${BannerController.BANNER_BASE_URL}/test")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus()
            .is2xxSuccessful
    }

    @Test
    @Order(6)
    fun `delete Non Exist Banner using delete from BannerController`() {

        val accessToken = authHelper.getAccessToken()
        client.delete()
            .uri("${BannerController.BANNER_BASE_URL}/test")
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus()
            .isNotFound
    }

    companion object : KLogging()
}