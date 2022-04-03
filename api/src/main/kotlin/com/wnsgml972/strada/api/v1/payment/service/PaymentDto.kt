package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.service.UserDto

data class PaymentDto(
    val aid: String?,
    val amountVo: AmountVo?,
    val approvedAt: String?,
    val cid: String,
    val createdAt: String,
    val itemName: String,
    val partnerOrderId: String,
    val partnerUserId: String,
    val paymentMethodType: String,
    val quantity: Int,
    val tid: String?,
    val paymentStatus: PaymentStatus,
    val userDto: UserDto,
    val id: Long
)
