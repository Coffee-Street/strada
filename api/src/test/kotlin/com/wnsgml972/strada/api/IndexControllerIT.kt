package com.wnsgml972.strada.api

import com.wnsgml972.strada.IntegrationWebTest
import mu.KLogging
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class IndexControllerIT @Autowired constructor(
    private val client: WebTestClient,
) : IntegrationWebTest() {

    @Test
    fun `get root path`() {
        client.get()
            .uri("/")
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<String>()
            .consumeWith { result ->
                result.responseBody.shouldNotBeNull()
                logger.debug { "result=${result.responseBody}" }
            }
    }

    @Test
    fun `ping server get pong`() {
        client.get()
            .uri(IndexController.INDEX_BASE_URL)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<String>()
            .consumeWith { result ->
                result.responseBody shouldBeEqualTo "PONG"
            }
    }

    companion object : KLogging()
}