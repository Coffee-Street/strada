package com.wnsgml972.strada.config.management

import mu.KLogging
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.ProblemModule
import org.zalando.problem.violations.ConstraintViolationProblemModule

/**
 * `application/json+problem` 을 활용하여 서버의 예외정보를 표준화된 JSON으로 제공할 수 있도록 해줍니다.
 *
 * 참고 : https://github.com/zalando/problem-spring-web
 *
 */
@Configuration
@ConditionalOnClass(ProblemModule::class)
class ProblemConfig {

    /**
     * Jackson ObjectMapper 에 Problem Module을 등록해서 제공합니다.
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    fun objectMapper(): ObjectMapper {
        logger.info { "Create Jackson ObjectMapper for Problem library." }
        return ObjectMapper()
            .registerModule(ProblemModule().withStackTraces())
    }

    @Bean
    fun problemModule() = ProblemModule()

    @Bean
    fun constraintViolationProblemModule() = ConstraintViolationProblemModule()

    companion object : KLogging() {
        const val ORDER_BEFORE_DEFAULT_EXCEPTION_HANDLER = -2
    }
}
