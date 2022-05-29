package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.service.UserDto
import java.time.LocalDateTime

data class PaymentDto(
    val aid: String?,
    val amountVo: AmountVo?,
    val approvedAt: LocalDateTime?,
    val cid: String,
    val createdAt: LocalDateTime?,
    val itemName: String,
    val partnerOrderId: String,
    val partnerUserId: String,
    val paymentMethodType: String?,
    val quantity: Int,
    val tid: String?,
    val paymentStatus: PaymentStatus,
    val userDto: UserDto,
)
