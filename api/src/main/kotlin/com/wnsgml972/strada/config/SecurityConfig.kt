package com.wnsgml972.strada.config

import com.wnsgml972.strada.api.v1.health.HealthCheckController
import com.wnsgml972.strada.api.v1.account.controller.AccountController
import com.wnsgml972.strada.security.JwtAuthorizationFilter
import com.wnsgml972.strada.security.OAuth2SecuritySpec
import com.wnsgml972.strada.security.TokenAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig @Autowired constructor(
    private val tokenAuthenticationProvider: TokenAuthenticationProvider,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.eraseCredentials(true)
            .authenticationProvider(tokenAuthenticationProvider)
    }

    override fun configure(http: HttpSecurity) {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(jwtAuthorizationFilter)
            .authorizeRequests()
                .antMatchers(
//                "/",
//                IndexController.INDEX_BASE_URL,
                    HealthCheckController.HEALTH_BASE_URL,
                    "${HealthCheckController.HEALTH_BASE_URL}/**",
                    AccountController.ACCOUNT_BASE_URL).permitAll()
                .antMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/**").permitAll()  //API test를 위해 모든 요청에 대해 jwt인증 해제
            .anyRequest().authenticated()

        OAuth2SecuritySpec().configure(http)
    }
}
