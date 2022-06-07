package com.wnsgml972.strada.api.v1.payment.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.wnsgml972.strada.exception.StradaIllegalStateException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap

@Component
class ObjectToMultiValueMapper@Autowired constructor(
    @Qualifier("KakaoObjectMapper")
    val objectMapper: ObjectMapper
) {

    final inline fun <reified O> convert(dto: O): LinkedMultiValueMap<String, String> =
        dto?.let {
            objectMapper
                .convertValue<Map<String, String>>(dto)
                .let { LinkedMultiValueMap<String, String>().apply { setAll(it) } }
        } ?: throw StradaIllegalStateException("dto is null")
}
