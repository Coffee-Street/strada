package com.wnsgml972.strada.api.v1.payment.service

import java.util.Date

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiReadyResponse(
    val tid: String,
    val next_redirect_app_url: String,
    val next_redirect_mobile_url: String,
    val next_redirect_pc_url: String,
    val android_app_scheme: String,
    val ios_app_scheme: String,
    val created_at: Date,
    var id: Long?
)
