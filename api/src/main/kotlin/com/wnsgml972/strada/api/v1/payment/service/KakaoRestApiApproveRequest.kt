package com.wnsgml972.strada.api.v1.payment.service

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiApproveRequest(
    val cid: String,
    val tid: String,
    val partnerOrderId: String,
    val partnerUserId: String,
    val pgToken: String,
)
