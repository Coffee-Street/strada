package com.wnsgml972.strada.api.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.wnsgml972.strada.UnitTest
import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.config.MeterFilter
import org.amshove.kluent.shouldNotBeEmpty
import org.amshove.kluent.shouldNotBeNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

class UserServiceTest @Autowired constructor(
    private val context: ApplicationContext
) : UnitTest() {

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
    fun `load problem beans`() {
        val mapper = context.getBeansOfType(ObjectMapper::class.java)
        mapper.shouldNotBeNull()
    }
}
