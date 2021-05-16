package com.wnsgml972.strada.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.wnsgml972.strada.IntegrationTest
import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.config.MeterFilter
import io.swagger.v3.oas.models.OpenAPI
import org.amshove.kluent.shouldNotBeEmpty
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


class StradaConfigTest @Autowired constructor(
    private val context: ApplicationContext
) : IntegrationTest() {

    @Test
    fun `context loading`() {
        context.shouldNotBeNull()
    }

    @Test
    fun `load micrometer beans`() {
        context.getBeansOfType(TimedAspect::class.java).shouldNotBeNull()
        context.getBeansOfType(CountedAspect::class.java).shouldNotBeNull()
        context.getBeansOfType(MeterFilter::class.java).shouldNotBeEmpty()
    }

    @Test
    fun `load jackson beans`() {
        val mapper = context.getBeansOfType(ObjectMapper::class.java)
        mapper.shouldNotBeNull()
    }

    @Test
    fun `load swagger beans`() {
        context.getBeansOfType(OpenAPI::class.java).shouldNotBeNull()
    }

    @Test
    fun `load security beans`() {
        context.getBeansOfType(WebSecurityConfigurerAdapter::class.java).shouldNotBeEmpty()
    }
}
