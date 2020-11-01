package com.wnsgml972.strada.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(
    authenticationManager: AuthenticationManager
): UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authRequest =
            UsernamePasswordAuthenticationToken(request.getAttribute("userEmail"), request.getAttribute("userPw"))
        setDetails(request, authRequest)
        return authenticationManager.authenticate(authRequest)
    }

    init {
        super.setAuthenticationManager(authenticationManager)
    }
}
