package com.wnsgml972.strada.security

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken(
    private val principal: Any,
    private val token: String,
    authorities: Set<GrantedAuthority>
) : AbstractAuthenticationToken(authorities) {

    override fun getCredentials(): Any = token
    override fun getPrincipal(): Any = principal
}