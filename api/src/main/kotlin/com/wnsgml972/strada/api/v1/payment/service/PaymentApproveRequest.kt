package com.wnsgml972.strada.api.v1.payment.service

@SuppressWarnings("ConstructorParameterNaming")
data class PaymentApproveRequest(
    val tid: String,
    val pg_token: String
)
