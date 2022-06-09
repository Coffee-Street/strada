package com.wnsgml972.strada.api.v1.payment.service

import java.util.Date

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiApproveResponse(
    val aid: String,
    val tid: String,
    val cid: String,
    val sid: String?,
    val partnerOrderId: String,
    val partnerUserId: String,
    val paymentMethodType: String,
    val amount:	AmountVo,
    val itemName: String,
    val itemCode: String?,
    val quantity: Integer,
    val createdAt: Date,
    val approvedAt: Date,
    val payload: String?
)
