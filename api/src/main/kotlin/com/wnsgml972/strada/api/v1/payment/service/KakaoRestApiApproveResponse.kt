package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.payment.domain.Amount
import java.time.LocalDateTime

@SuppressWarnings("ConstructorParameterNaming")
data class KakaoRestApiApproveResponse(
    val aid: String,
    val tid: String,
    val cid: String,
    val sid: String?,
    val partner_order_id: String,
    val partner_user_id: String,
    val payment_method_type: String,
    val amount:	Amount,
    // val card_info:	CardInfo,
    val item_name:	String,
    val item_code:	String?,
    val quantity:	Integer,
    val created_at: LocalDateTime,
    val approved_at: LocalDateTime,
    val payload: String?
)
