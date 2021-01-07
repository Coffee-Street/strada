package com.wnsgml972.strada.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

@Configuration
class RestApiTemplateConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModule(JavaTimeModule())
            .registerModule(KotlinModule())
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
    }

    @Bean
    fun apiRestTemplate(): RestTemplate {
        val restTemplate = RestTemplateBuilder()
            .build()

        val converter = MappingJackson2HttpMessageConverter()
        converter.objectMapper = objectMapper()

        val converters = restTemplate.messageConverters
        converters.clear()
        converters.add(converter)
        converters.add(StringHttpMessageConverter())

        return restTemplate
    }
}
