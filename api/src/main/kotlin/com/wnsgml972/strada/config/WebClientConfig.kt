package com.wnsgml972.strada.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfig @Autowired constructor(
    private val kakaoApiProperties: KakaoApiProperties,
    @Qualifier("KakaoObjectMapper")
    private val kakaoObjectMapper: ObjectMapper
) {
    @Bean("kakaoApiWebClient")
    fun kakaoApiWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl(kakaoApiProperties.host)
            .clientConnector(ReactorClientHttpConnector(createDefaultHttpClient()))
            .codecs { configurer ->
                configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(kakaoObjectMapper))
                configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(kakaoObjectMapper))
            }
            .build()
    }

    private fun createDefaultHttpClient() =
        HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, DEFAULT_CONNECT_TIMEOUT_MILLIS)
            .responseTimeout(Duration.ofMillis(DEFAULT_RESPONSE_TIMEOUT_MILLIS))
            .doOnConnected { conn: Connection ->
                conn.addHandlerLast(ReadTimeoutHandler(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                    .addHandlerLast(WriteTimeoutHandler(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
            }

    companion object : KLogging() {
        private const val DEFAULT_CONNECT_TIMEOUT_MILLIS = 5000
        private const val DEFAULT_RESPONSE_TIMEOUT_MILLIS = 5000L
        private const val DEFAULT_READ_TIMEOUT_MILLIS = 5000L
        private const val DEFAULT_WRITE_TIMEOUT_MILLIS = 5000L
    }
}
