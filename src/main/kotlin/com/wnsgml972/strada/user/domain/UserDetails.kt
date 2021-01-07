package com.wnsgml972.strada.user.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetails(
    private val user: User,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return user.isEnabled
    }

    override fun isAccountNonLocked(): Boolean {
        return user.isEnabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return user.isEnabled
    }

    override fun isEnabled(): Boolean {
        return user.isEnabled
    }
}