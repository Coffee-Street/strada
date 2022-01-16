package com.wnsgml972.strada

import com.wnsgml972.strada.api.v1.account.service.AccessTokenRequest
import com.wnsgml972.strada.api.v1.account.service.JwtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthHelper @Autowired constructor(
    private val jwtService: JwtService
) {
    private val accessTokenRequest = AccessTokenRequest("010-1234-1234")

    fun getAccessToken(): String {
        return jwtService
            .createToken(accessTokenRequest.phoneNumber)
    }
}
