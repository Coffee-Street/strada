package com.wnsgml972.strada.api

import com.wnsgml972.strada.AuthHelper
import com.wnsgml972.strada.IntegrationTest
import mu.KLogging
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class IndexControllerIT @Autowired constructor(
    private val client: WebTestClient,
    private val authHelper: AuthHelper,
) : IntegrationTest() {

    @Test
    fun `get ping auth fail, forbidden`() {
        client.get()
            .uri(IndexController.INDEX_BASE_URL)
            .exchange()
            .expectStatus().isForbidden
    }

    @Test
    fun `get ping auth success`() {
        val accessToken = authHelper.getAccessToken()

        client.get()
            .uri(IndexController.INDEX_BASE_URL)
            .header("Authorization", "Bearer $accessToken")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<String>()
            .consumeWith { result ->
                result.responseBody shouldBeEqualTo "PONG"
                logger.debug { "result=${result.responseBody}" }
            }
    }

    companion object : KLogging()
}
