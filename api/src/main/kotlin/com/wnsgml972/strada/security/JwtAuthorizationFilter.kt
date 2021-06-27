package com.wnsgml972.strada.security

import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.wnsgml972.strada.api.v1.account.service.AccessTokenRequest
import com.wnsgml972.strada.exception.StradaUnAuthorizedException
import mu.KLogging
import org.springframework.security.authentication.AuthenticationManager
import com.wnsgml972.strada.exception.StradaIllegalStateException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.stereotype.Component
import javax.servlet.FilterChain
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtProperties: JwtProperties,
    private val objectMapper: ObjectMapper
) : BasicAuthenticationFilter(authenticationManager) {

    companion object : KLogging()

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header: String? = request.getHeader(jwtProperties.header)
        header?.let { token ->
            if (token.startsWith("${jwtProperties.headerPrefix} ")) {
                try {
                    val jwt = decodeJWT(token)
                    val tokenPayload = objectMapper.readValue(
                        jwt.subject,
                        AccessTokenRequest::class.java
                    )
                    logger.debug("token payload: $tokenPayload")
                    val userPrincipal = getPrincipal(tokenPayload)
                    val auth = JwtAuthenticationToken(userPrincipal, token, userPrincipal.authorities)
                    SecurityContextHolder.getContext().authentication = auth
                } catch (e: TokenExpiredException) {
                    logger.error("token expired", e)
                    request.setAttribute(RequestDispatcher.ERROR_EXCEPTION,
                        StradaUnAuthorizedException("token expired"))
                    throw StradaUnAuthorizedException("token expired")
                } catch (e: JsonProcessingException) {
                    logger.error("error occurred while processing payload", e)
                    request.setAttribute(RequestDispatcher.ERROR_EXCEPTION,
                        StradaIllegalStateException("jwt parse error"))
                    throw StradaIllegalStateException("jwt parse error")
                } catch (e: IllegalArgumentException) {
                    logger.error("token is wrong", e)
                    request.setAttribute(RequestDispatcher.ERROR_EXCEPTION,
                        StradaUnAuthorizedException("invalid token"))
                    throw StradaUnAuthorizedException("invalid token")
                }
            }
        }
        chain.doFilter(request, response)
    }

    private fun decodeJWT(token: String): DecodedJWT {
        val decodedJwtList = jwtProperties.decodeJWT(token)
        return if (decodedJwtList.isNotEmpty()) {
            decodedJwtList.first()
        } else {
            throw IllegalArgumentException("failed to decode token")
        }
    }

    private fun getPrincipal(accessTokenRequest: AccessTokenRequest): JwtPrincipal = JwtPrincipal(
        JwtPrincipal.PrincipalPhoneNumber(accessTokenRequest.phoneNumber),
        "username",
        "",
        true,
        true,
        true,
        true,
        setOf(SimpleGrantedAuthority("ROLE_USER"))
    )
}
