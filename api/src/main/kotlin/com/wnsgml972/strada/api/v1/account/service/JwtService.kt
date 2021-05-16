package com.wnsgml972.strada.api.v1.account.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.ObjectMapper
import com.wnsgml972.strada.security.JwtProperties
import mu.KLogging
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date

@Service
class JwtService(
    private val jwtProperties: JwtProperties,
    private val objectMapper: ObjectMapper,
) {

    fun createToken(
        phoneNumber: String
    ): AccessTokenResponse {
        val subject = objectMapper.writeValueAsString(AccessTokenRequest(phoneNumber))
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
        const val SECONDS_TO_ADD: Long = 3600
    }
}
