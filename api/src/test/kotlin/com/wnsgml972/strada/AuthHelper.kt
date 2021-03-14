package com.wnsgml972.strada

import com.wnsgml972.strada.api.v1.account.controller.AccountController
import com.wnsgml972.strada.api.v1.account.service.AccessTokenResponse
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult

class AuthHelper(
    private val client: WebTestClient,
    private val phoneNumber: String = "010-2222-2222",
) {

    suspend fun getAccessToken(): String {
        return client.post()
            .uri(AccountController.ACCOUNT_BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(
                """
                {
                    "phoneNumber": $phoneNumber
                }
                """.trimIndent()
            )
            .exchange()
            .returnResult<AccessTokenResponse>()
            .responseBody
            .awaitFirst()
            .accessToken
    }
}