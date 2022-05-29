package com.wnsgml972.strada.api.v1.kakao.service

import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiApproveRequest
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiApproveResponse
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiReadyRequest
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiReadyResponse
import com.wnsgml972.strada.api.v1.payment.service.ObjectToMultiValueMapper
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
    private val kakaoApiWebClient: WebClient,
    private val objectToMultiValueMapper: ObjectToMultiValueMapper,
) {
    fun readyPayment(
        targetUserId: String,
        kakaoRestApiReadyRequest: KakaoRestApiReadyRequest
    ): KakaoRestApiReadyResponse {
        // 5xx error 에 대해 1번 재시도 합니다.
        val apiName = "readyPayment"
        return kakaoApiWebClient.post()
            .uri { it.path("/ready").build() }
            .header("Authorization", "KakaoAK " + "b3c9f7176efadd6a944606020fefd4da")
            .bodyValue(objectToMultiValueMapper.convert<String, String>(kakaoRestApiReadyRequest))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { WebClientExceptionHandler.onStatus4XXClientError(it) }
            .onStatus(HttpStatus::is5xxServerError) { WebClientExceptionHandler.onStatus5XXServerError(apiName, it) }
            .bodyToMono(KakaoRestApiReadyResponse::class.java)
            .retryWhen(
                Retry.max(1).filter { throwable -> throwable is StradaIllegalStateException }
            )
            .block(Duration.ofMillis(READY_API_TIMEOUT_MILLIS))
            ?: throw StradaIllegalStateException("Failed to $apiName api")
    }

    fun approvePayment(
        kakaoRestApiApproveRequest: KakaoRestApiApproveRequest
    ): KakaoRestApiApproveResponse {
        val apiName = "approvePayment"
        return kakaoApiWebClient.post()
            .uri { it.path("/approve").build() }
            .header("Authorization", "KakaoAK " + "b3c9f7176efadd6a944606020fefd4da")
            .bodyValue(objectToMultiValueMapper.convert<String, String>(kakaoRestApiApproveRequest))
            .retrieve()
            .onStatus(HttpStatus::is4xxClientError) { WebClientExceptionHandler.onStatus4XXClientError(it) }
            .onStatus(HttpStatus::is5xxServerError) { WebClientExceptionHandler.onStatus5XXServerError(apiName, it) }
            .bodyToMono(KakaoRestApiApproveResponse::class.java)
            .retryWhen(
                Retry.max(1).filter { throwable -> throwable is StradaIllegalStateException }
            )
            .block(Duration.ofMillis(READY_API_TIMEOUT_MILLIS))
            ?: throw StradaIllegalStateException("Failed to $apiName api")
    }

    companion object : KLogging() {
        private const val BASE_KAKAO_URL = "/todo"
        private const val READY_URL = "$BASE_KAKAO_URL/ready"
        private const val READY_API_TIMEOUT_MILLIS = 2000L
    }
}
