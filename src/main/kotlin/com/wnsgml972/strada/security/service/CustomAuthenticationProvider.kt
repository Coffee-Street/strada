package com.wnsgml972.strada.security.service

import com.wnsgml972.strada.user.service.UserDetailsDto
import com.wnsgml972.strada.user.service.UserDetailsServiceImpl
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.Optional

class CustomAuthenticationProvider(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication? {
        val token = authentication as UsernamePasswordAuthenticationToken
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        val username = token.name
        val userPassword = token.credentials as String
        // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        val userDetailsDto = userDetailsServiceImpl.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetailsDto, userPassword, userDetailsDto.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}