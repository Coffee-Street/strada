package com.wnsgml972.strada.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class JwtPrincipal(
    val id: Long,
    val phoneNumber: PrincipalPhoneNumber,
    private val username: String,
    private val password: String,
    private val enabled: Boolean,
    private val credentialsNonExpired: Boolean,
    private val accountNonExpired: Boolean,
    private val accountNonLocked: Boolean,
    private val authorities: Set<GrantedAuthority>
) : UserDetails {
    override fun getAuthorities(): Set<GrantedAuthority> = authorities
    override fun isEnabled(): Boolean = enabled
    override fun getUsername(): String = username
    override fun isCredentialsNonExpired(): Boolean = credentialsNonExpired
    override fun getPassword(): String = password
    override fun isAccountNonExpired(): Boolean = accountNonExpired
    override fun isAccountNonLocked(): Boolean = accountNonLocked

    data class PrincipalPhoneNumber (
        val number: String
    )
}