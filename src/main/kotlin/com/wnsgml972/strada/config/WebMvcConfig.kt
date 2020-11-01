package com.wnsgml972.strada.config

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.ser.std.DateSerializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Configuration
@EnableWebMvc
class WebMvcConfig : WebMvcConfigurer {

    private val CLASSPATH_RESOURCE_LOCATIONS = listOf(
        "classpath:/static/", "classpath:/public/", "classpath:/",
        "classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/"
    ).toString()

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(MappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder().build()))
        super.configureMessageConverters(converters)
    }

    fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder {
        return Jackson2ObjectMapperBuilder()
            .failOnUnknownProperties(false) // SpringBoot default
            .featuresToDisable(MapperFeature.DEFAULT_VIEW_INCLUSION) // SpringBoot default
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .serializerByType(
                Date::class.java,
                DateSerializer(
                    false,
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
                        timeZone = TimeZone.getTimeZone("UTC")
                    })
            )
            .serializerByType(
                LocalDateTime::class.java,
                LocalDateTimeSerializer(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                )
            )
            .deserializerByType(
                LocalDateTime::class.java,
                LocalDateTimeDeserializer(DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault()))
            )
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // swagger-ui
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/")
        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS)
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        // /에 해당하는 url mapping을 /common/test로 forward한다.
        registry.addViewController("/").setViewName("forward:/index")
        // 우선순위를 가장 높게 잡는다.
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }
}