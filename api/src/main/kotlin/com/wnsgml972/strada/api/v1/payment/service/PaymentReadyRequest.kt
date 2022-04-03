package com.wnsgml972.strada.api.v1.payment.service

data class PaymentReadyRequest(
    val cid: String,
    val createdAt: String,
    val itemName: String,
    val partnerOrderId: String,
    val partnerUserId: String,
    val paymentMethodType: String,
    val quantity: Int,
)
