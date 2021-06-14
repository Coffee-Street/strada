package com.wnsgml972.strada.api.health

import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.health.HealthCheckController
import com.wnsgml972.strada.api.v1.item.bread.service.BreadDTO
import mu.KLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.reactive.server.WebTestClient

class HealthControllerIT @Autowired constructor(
    private val client: WebTestClient,
) : IntegrationTest() {

    @Test
    fun `health check`() {
        client.get()
            .uri(HealthCheckController.HEALTH_BASE_URL)
            .exchange()
            .expectStatus().is2xxSuccessful

    }

    companion object : KLogging()
}