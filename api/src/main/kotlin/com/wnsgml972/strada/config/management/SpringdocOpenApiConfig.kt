package com.wnsgml972.strada.config.management

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnClass(OpenAPI::class)
class SpringdocOpenApiConfig {

    @Bean
    fun customOpenAPI(@Value("\${springdoc.version}") appVersion: String): OpenAPI =
        OpenAPI()
            .components(Components()
                .addSecuritySchemes("basicScheme",
                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
            .info(Info()
                .title("Strada API")
                .description("APIs for Strada")
                .version(appVersion))
}
