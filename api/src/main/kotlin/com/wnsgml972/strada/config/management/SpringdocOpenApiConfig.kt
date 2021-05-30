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
                .addSecuritySchemes(OPEN_API_BEARER_KEY,
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .`in`(SecurityScheme.In.HEADER)
                        .scheme(OPEN_API_BEARER_SCHEMA)
                        .bearerFormat(OPEN_API_BEARER_FORMAT)
                        .description("""
                        인증이 필요한 API 는 발급받은 토큰을 HTTP Header 에 아래와 같이 추가하여 보내야 합니다.
                        ```
                        Authorization: Bearer {YOUR-TOKEN}
                        ```
                        """.trimIndent()))
            ).info(Info()
                .title("Strada API")
                .description("APIs for Strada")
                .version(appVersion))

    companion object {
        const val OPEN_API_BEARER_KEY = "bearer-key"
        private const val OPEN_API_BEARER_SCHEMA = "bearer"
        private const val OPEN_API_BEARER_FORMAT = "JWT"
    }
}
