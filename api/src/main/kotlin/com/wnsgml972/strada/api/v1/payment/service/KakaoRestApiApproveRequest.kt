package com.wnsgml972.strada.api.v1.payment.service

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiReadyRequest(
    val cid: String,
    val partner_order_id: String,
    val partner_user_id: String,
    val item_name: String,
    val quantity: String,
    val total_amount: String,
    val vat_amount: String,
    val tax_free_amount: String,
    val approval_url: String,
    val fail_url: String,
    val cancel_url: String
)
