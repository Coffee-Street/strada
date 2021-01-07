package com.wnsgml972.strada.config.management

import io.micrometer.core.aop.CountedAspect
import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.config.MeterFilter
import mu.KLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

/**
 * Micrometer로 metrics를 측정하기 위한 환경설정
 * NOTE: AoP 를 사용하므로, `spring-boot-starter-aop` 를 참조해야 합니다.
 */
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnClass(MeterRegistry::class)
class MicrometerConfig {

    companion object : KLogging() {
        private val ignoredPaths = listOf("/actuator", "/swagger", "/v2/api-docs", "/v3/api-docs")
    }

    /**
     * `@Counted` annotation이 적용된 메소드의 실행 횟수를 측정하는 aspect 입니다.
     *
     * @param registry Micrometer용 [MeterRegistry]
     * @return [CountedAspect] instance
     */
    @Bean
    fun countedAspect(registry: MeterRegistry): CountedAspect =
        CountedAspect(registry)

    /**
     * `@Timed` annotation이 적용된 메소드의 실행 시간을 측정해주는 aspect 입니다.
     *
     * @param registry Micrometer용 [MeterRegistry]
     * @return [TimedAspect] instance
     */
    @Bean
    fun timedAspect(registry: MeterRegistry): TimedAspect =
        TimedAspect(registry)

    @Bean
    @ConditionalOnMissingBean
    fun metricsHttpServerUriFilter(): MeterFilter =
        MeterFilter
            .deny { id ->
                val uriTag = id?.getTag("uri")
                uriTag?.let { uri ->
                    ignoredPaths.any { ignoredPath -> uri.contains(ignoredPath) }
                } ?: false
            }
}
