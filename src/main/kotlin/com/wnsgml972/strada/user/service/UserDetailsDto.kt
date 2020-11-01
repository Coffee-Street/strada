package com.wnsgml972.strada.user.service

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsDto(
    private val userDto: UserDto,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    fun getUserDto() = userDto

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return userDto.password
    }

    override fun getUsername(): String {
        return userDto.username
    }

    override fun isAccountNonExpired(): Boolean {
        return userDto.isEnabled
    }

    override fun isAccountNonLocked(): Boolean {
        return userDto.isEnabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return userDto.isEnabled
    }

    override fun isEnabled(): Boolean {
        return userDto.isEnabled
    }
}