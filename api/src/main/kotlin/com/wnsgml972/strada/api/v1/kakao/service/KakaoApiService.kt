package com.wnsgml972.strada.api.v1.kakao.service

import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.handler.WebClientExceptionHandler
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.util.retry.Retry
import java.time.Duration

@Service
class KakaoApiService @Autowired constructor(
    @Qualifier("kakaoApiWebClient")
    private val kakaoApiWebClient: WebClient
) {

    fun readyPayment(
        targetUserId: Long,
        limit: Int,
        cursor: String?
    ): KakaoReadyResponse {
        // 5xx error 에 대해 1번 재시도 합니다.
        val apiName = "readyPayment"
        return kakaoApiWebClient.get()
            .uri { it.build() }
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { WebClientExceptionHandler.onStatus4XXClientError(it) }
            .onStatus(HttpStatus::is5xxServerError) { WebClientExceptionHandler.onStatus5XXServerError(apiName, it) }
            .bodyToMono(KakaoReadyResponse::class.java)
            .doOnSuccess { logger.debug { "[$apiName] api success $it" } }
            .retryWhen(
                Retry.max(1).filter { throwable -> throwable is StradaIllegalStateException }
            )
            .block(Duration.ofMillis(READY_API_TIMEOUT_MILLIS))
            ?: throw StradaIllegalStateException("Failed to $apiName api")
    }

    data class KakaoReadyResponse(
        val message: String
    )

    companion object : KLogging() {
        private const val BASE_KAKAO_URL = "/todo"
        private const val READY_URL = "$BASE_KAKAO_URL/ready"

        private const val READY_API_TIMEOUT_MILLIS = 2000L
    }
}
