package com.wnsgml972.strada.api.auth

import com.wnsgml972.strada.IntegrationTest
import com.wnsgml972.strada.api.v1.account.controller.AccountController
import com.wnsgml972.strada.api.v1.account.service.AccessTokenRequest
import com.wnsgml972.strada.api.v1.account.service.AccessTokenResponse
import mu.KLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class AccountControllerIT @Autowired constructor(
    private val client: WebTestClient,
) : IntegrationTest() {

    @Test
    fun `account controller get token`() {
        val accessTokenRequest = AccessTokenRequest("010-1234-1234")

        client.post()
            .uri(AccountController.ACCOUNT_BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(accessTokenRequest)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody<AccessTokenResponse>()
    }

    companion object : KLogging()
}
