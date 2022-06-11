package com.wnsgml972.strada.handler

import com.wnsgml972.strada.exception.StradaBadRequestException
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

object WebClientExceptionHandler {
    fun onStatus4XXClientError(response: ClientResponse): Mono<Throwable> {
        return if (response.statusCode() == HttpStatus.NOT_FOUND) {
            response.bodyToMono<String>()
                .flatMap { error ->
                    Mono.error<StradaNotFoundException> { StradaNotFoundException(error) }
                }
        } else {
            response.bodyToMono<String>()
                .flatMap { error ->
                    Mono.error<StradaBadRequestException> { StradaBadRequestException(error) }
                }
        }
    }

    fun onStatus5XXServerError(apiName: String, response: ClientResponse): Mono<Throwable> {
        return response.bodyToMono<String>()
            .flatMap { error ->
                Mono.error<StradaIllegalStateException> { StradaIllegalStateException("[$apiName] error: $error") }
            }
    }
}
