package com.wnsgml972.strada

import com.wnsgml972.strada.api.v1.account.service.AccessTokenRequest
import com.wnsgml972.strada.api.v1.account.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthHelper @Autowired constructor(
    private val jwtService: JwtService
) {
    val phoneNumber = "010-1234-1234"
    private val accessTokenRequest = AccessTokenRequest(phoneNumber)

    fun getAccessToken(): String {
        return jwtService
            .createToken(accessTokenRequest.phoneNumber)
            .accessToken
    }
}
