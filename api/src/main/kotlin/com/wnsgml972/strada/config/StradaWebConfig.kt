package com.wnsgml972.strada.config

import com.wnsgml972.strada.config.management.MicrometerConfig
import com.wnsgml972.strada.config.management.ProblemConfig
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import org.springframework.context.annotation.Import

@Import(
    MicrometerConfig::class,
    ProblemConfig::class,
    SpringdocOpenApiConfig::class,
    RestApiTemplateConfig::class,
    SecurityConfig::class,
)
class StradaWebConfig
