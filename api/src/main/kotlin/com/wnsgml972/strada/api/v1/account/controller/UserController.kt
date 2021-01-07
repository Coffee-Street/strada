package com.wnsgml972.strada.api.v1.account.controller

import BASE_URL_V1
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.wnsgml972.strada.api.v1.account.controller.TokenController.Companion.TOKEN_BASE_URL
import com.wnsgml972.strada.security.user.domain.JwtProperties
import com.wnsgml972.strada.api.v1.account.service.AccessTokenRequest
import com.wnsgml972.strada.api.v1.account.service.AccessTokenResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.Date
import javax.validation.Valid

@RestController
@RequestMapping(path = [TOKEN_BASE_URL])
@Tag(
    name = "Account",
    description = """유저에게 토큰을 발급해주는 API"""
)
class TokenController @Autowired constructor(
    private val jwtProperties: JwtProperties,
    private val objectMapper: ObjectMapper
) {

    @PostMapping
    @Operation(summary = "토큰 발급")
    @ApiResponse(responseCode = "200", description = "토큰 값")
    fun createToken(
        @Parameter(
            name = "payload",
            description = "토큰 발급을 위한 userId와 phoneNumber를 입력해 주세요. 유효 시간은 60분입니다.",
            required = true,
            examples = [
                ExampleObject(name = "userId", value = "123,456,789"),
                ExampleObject(name = "phoneNumber", value = "010-1111-1111"),
            ]
        )
        @Valid @RequestBody accessTokenResponse: AccessTokenRequest
    ): AccessTokenResponse {
        val subject = objectMapper.writeValueAsString(accessTokenResponse)
        val privateKey = jwtProperties.keyPair[0].privateKey
        val publicKey = jwtProperties.keyPair[0].publicKey
        return AccessTokenResponse(
            JWT.create()
                .withSubject(subject)
                .withAudience(JwtProperties.AUDIENCE)
                .withIssuer(JwtProperties.ISSUER)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(SECONDS_TO_ADD)))
                .sign(Algorithm.RSA256(publicKey, privateKey))
        )
    }

    companion object : KLogging() {
        private const val TOKEN_SERVICE_NAME = "account"
        const val TOKEN_BASE_URL = "$BASE_URL_V1/$TOKEN_SERVICE_NAME"
        const val SECONDS_TO_ADD: Long = 3600
    }
}
