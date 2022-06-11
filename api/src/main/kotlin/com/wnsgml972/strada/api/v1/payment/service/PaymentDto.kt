package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.service.UserDto
import java.util.Date

data class PaymentDto(
    val aid: String?,
    val amountVo: AmountVo?,
    val approvedAt: Date?,
    val cid: String,
    val createdAt: Date?,
    val itemName: String,
    val partnerOrderId: String,
    val partnerUserId: String,
    val paymentMethodType: String?,
    val quantity: Int,
    val tid: String?,
    val paymentStatus: PaymentStatus,
    val userDto: UserDto,
)
