package com.wnsgml972.strada.api.v1.payment.service

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiApproveRequest(
    val cid: String,
    val tid: String,
    val partner_order_id: String,
    val partner_user_id: String,
    val pg_token: String,
)
