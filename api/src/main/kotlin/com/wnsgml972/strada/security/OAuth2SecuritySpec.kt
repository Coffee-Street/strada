package com.wnsgml972.strada.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity

class OAuth2SecuritySpec {
    fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .formLogin().disable()
            .logout().disable()
            .httpBasic().disable()
            .exceptionHandling()
    }
}
