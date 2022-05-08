package com.wnsgml972.strada.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("api.kakao")
class KakaoApiProperties(
    val host: String,
)
