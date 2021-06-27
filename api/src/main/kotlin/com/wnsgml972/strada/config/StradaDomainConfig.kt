package com.wnsgml972.strada.config

import com.wnsgml972.strada.security.JwtProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@EnableConfigurationProperties(
    JwtProperties::class,
)
@Import(
    DataSourceConfiguration::class,
)
@ComponentScan(basePackages = ["com.wnsgml972.strada.*"])
class StradaDomainConfig
