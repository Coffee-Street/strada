package com.wnsgml972.strada.api.v1.payment.service

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiReadyRequest(
    val cid: String,
    val partnerOrderId: String,
    val partnerUserId: String,
    val itemName: String,
    val quantity: String,
    val totalAmount: String,
    val vatAmount: String,
    val taxFreeAmount: String,
    val approvalUrl: String,
    val failUrl: String,
    val cancelUrl: String
)
