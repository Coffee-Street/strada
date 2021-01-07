package com.wnsgml972.strada.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class TokenAuthenticationManager: AuthenticationManager {
    override fun authenticate(authentication: Authentication?): Authentication? {
        if (authentication is JwtAuthenticationToken) {
            authentication.isAuthenticated = true
        }
        return authentication
    }
}