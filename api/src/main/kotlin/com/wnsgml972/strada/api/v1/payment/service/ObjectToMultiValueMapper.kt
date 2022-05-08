package com.wnsgml972.strada.api.v1.payment.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap

@Component
class ObjectToMultiValueMapper@Autowired constructor(
    val objectMapper: ObjectMapper
) {

    final inline fun <reified K, reified V> convert(dto: KakaoRestApiReadyRequest): LinkedMultiValueMap<K, V> =
        objectMapper
            .convertValue<Map<K, V>>(dto)
            .let { LinkedMultiValueMap<K, V>().apply { setAll(it) } }
}
