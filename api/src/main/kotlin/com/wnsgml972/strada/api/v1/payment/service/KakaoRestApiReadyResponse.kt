package com.wnsgml972.strada.api.v1.payment.service

import java.util.Date

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiReadyResponse(
    val tid: String,
    val nextRedirectAppUrl: String,
    val nextRedirectMobileUrl: String,
    val nextRedirectPcUrl: String,
    val androidAppScheme: String,
    val iosAppScheme: String,
    val createdAt: Date,
)
