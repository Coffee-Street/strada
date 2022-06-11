package com.wnsgml972.strada.config

import com.wnsgml972.strada.config.management.MicrometerConfig
import com.wnsgml972.strada.config.management.ProblemConfig
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@EnableConfigurationProperties(
    KakaoApiProperties::class,
)
@Import(
    MicrometerConfig::class,
    ProblemConfig::class,
    SpringdocOpenApiConfig::class,
    RestApiTemplateConfig::class,
    SecurityConfig::class,
    ExecutorConfig::class,
    AsyncConfig::class,
    WebClientConfig::class,
)
class StradaWebConfig
