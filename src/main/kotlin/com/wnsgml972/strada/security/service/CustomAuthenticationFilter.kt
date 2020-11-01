package com.wnsgml972.strada.security.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.wnsgml972.strada.common.exception.NotFoundException
import com.wnsgml972.strada.user.service.UserDto
import com.wnsgml972.strada.user.service.UserSignUpOrLoginRequest
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(
    authenticationManager: AuthenticationManager
): UsernamePasswordAuthenticationFilter() {

    init {
        super.setAuthenticationManager(authenticationManager)
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        val username = request.getParameter("username")
        val authRequest = UsernamePasswordAuthenticationToken(username, username)
        setDetails(request, authRequest)
        return authenticationManager.authenticate(authRequest)
    }
}