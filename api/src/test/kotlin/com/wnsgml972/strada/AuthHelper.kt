package com.wnsgml972.strada

import com.wnsgml972.strada.api.v1.account.controller.AccountController
import com.wnsgml972.strada.api.v1.account.service.AccessTokenRequest
import com.wnsgml972.strada.api.v1.account.service.AccessTokenResponse
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult

class AuthHelper @Autowired constructor(
    private val client: WebTestClient
) {
    private val accessTokenRequest = AccessTokenRequest("010-1234-1234")

    fun getAccessToken(): String = runBlocking {
        return@runBlocking client.post()
            .uri(AccountController.ACCOUNT_BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(accessTokenRequest)
            .exchange()
            .returnResult<AccessTokenResponse>()
            .responseBody
            .awaitFirst()
            .accessToken
    }
}