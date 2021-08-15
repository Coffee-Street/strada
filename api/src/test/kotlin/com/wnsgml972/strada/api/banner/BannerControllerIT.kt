package com.wnsgml972.strada.api.banner

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.banner.service.BannerInsertRequest
import com.wnsgml972.strada.api.v1.health.HealthCheckController
import com.wnsgml972.strada.api.v1.item.bread.controller.admin.BreadController
import mu.KLogging
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class BannerControllerIT@Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
) : IntegrationTest() {


    @BeforeEach
    fun `insert dummy date before test`(){

        val bannerInsertRequest = BannerInsertRequest(
            "dummy1",
            10,
            "dummy title",
            "http://dummy",
            "this is dummy",
        )

        val accessToken = authHelper.getAccessToken()
        client.post()
            .uri(BreadController.BREAD_BASE_URL)
            .header("Authorization", "Bearer $accessToken")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(bannerInsertRequest)
            .exchange()
    }

    companion object : KLogging()
}