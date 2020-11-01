package com.wnsgml972.strada.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class CustomAuthenticationProvider(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val userDetailsService: UserDetailsService
) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication as UsernamePasswordAuthenticationToken
        // AuthenticaionFilter에서 생성된 토큰으로부터 아이디와 비밀번호를 조회함
        val userEmail = token.name
        val userPw = token.credentials as String
        // UserDetailsService를 통해 DB에서 아이디로 사용자 조회
        val userDetails = userDetailsService!!.loadUserByUsername(userEmail)
        if (!passwordEncoder!!.matches(userPw, userDetails.password)) {
            throw BadCredentialsException(userDetails.username + "Invalid password")
        }
        return UsernamePasswordAuthenticationToken(userDetails, userPw, userDetails.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}