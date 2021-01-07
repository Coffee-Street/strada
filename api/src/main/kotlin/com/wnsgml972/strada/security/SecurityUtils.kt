package com.wnsgml972.strada.security

import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtils {
    fun getPrincipal() = SecurityContextHolder.getContext().authentication.principal as JwtPrincipal
}