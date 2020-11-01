package com.wnsgml972.strada.config.security

import com.wnsgml972.strada.security.CustomAuthenticationFilter
import com.wnsgml972.strada.security.CustomAuthenticationProvider
import com.wnsgml972.strada.security.CustomLoginSuccessHandler
import com.wnsgml972.strada.user.service.UserDetailsServiceImpl
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) : WebSecurityConfigurerAdapter() {

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    override fun configure(web: WebSecurity) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests() // /about 요청에 대해서는 로그인을 요구함
            .antMatchers("/about").authenticated() // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
            .antMatchers("/admin").hasRole("ADMIN") // 나머지 요청에 대해서는 로그인을 요구하지 않음
            .anyRequest().permitAll()
            .and() // 로그인하는 경우에 대해 설정함
            .formLogin() // 로그인 페이지를 제공하는 URL을 설정함
            .loginPage("/user/loginView") // 로그인 성공 URL을 설정함
            .successForwardUrl("/index") // 로그인 실패 URL을 설정함
            .failureForwardUrl("/index")
            .permitAll()
            .and()
            .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun bCryptPasswordEncoder() = BCryptPasswordEncoder()

    @Bean
    @Throws(Exception::class)
    fun customAuthenticationFilter(): CustomAuthenticationFilter {
        val customAuthenticationFilter = CustomAuthenticationFilter(authenticationManager())
        customAuthenticationFilter.setFilterProcessesUrl("/user/login")
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler())
        customAuthenticationFilter.afterPropertiesSet()
        return customAuthenticationFilter
    }

    @Bean
    fun customLoginSuccessHandler() = CustomLoginSuccessHandler()

    @Bean
    fun customAuthenticationProvider()
        = CustomAuthenticationProvider(bCryptPasswordEncoder(), userDetailsServiceImpl)

    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider())
    }
}