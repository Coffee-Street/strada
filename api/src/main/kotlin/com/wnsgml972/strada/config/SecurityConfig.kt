package com.wnsgml972.strada.config

import com.wnsgml972.strada.api.v1.account.controller.AccountController
import com.wnsgml972.strada.api.v1.health.HealthCheckController
import com.wnsgml972.strada.security.JwtAuthorizationFilter
import com.wnsgml972.strada.security.OAuth2SecuritySpec
import com.wnsgml972.strada.security.TokenAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@SuppressWarnings("SpreadOperator")
class SecurityConfig @Autowired constructor(
    private val tokenAuthenticationProvider: TokenAuthenticationProvider,
    private val jwtAuthorizationFilter: JwtAuthorizationFilter,
    private val kakaoApiProperties: KakaoApiProperties,
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
                "/",
                HealthCheckController.HEALTH_BASE_URL,
                "${HealthCheckController.HEALTH_BASE_URL}/**",
                AccountController.ACCOUNT_BASE_URL).permitAll()
            .antMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**",
                *kakaoApiProperties.redirection.toTypedArray()
            ).permitAll()
            .anyRequest().authenticated()
            .and()
            .cors()

        OAuth2SecuritySpec().configure(http)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("http://127.0.0.1:3000")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
