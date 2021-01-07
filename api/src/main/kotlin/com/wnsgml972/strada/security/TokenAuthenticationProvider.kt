package com.wnsgml972.strada.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class TokenAuthenticationProvider(
    private val tokenAuthenticationManager: TokenAuthenticationManager
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication? {
        return tokenAuthenticationManager.authenticate(authentication)
    }

    override fun supports(authentication: Class<*>): Boolean =
        JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
}
