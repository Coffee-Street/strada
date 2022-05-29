package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.account.service.toDto
import com.wnsgml972.strada.api.v1.payment.domain.Amount
import com.wnsgml972.strada.api.v1.payment.domain.Payment

fun KakaoRestApiReadyRequest.toEntity(user: User, id: Long? = null) = Payment.of(
    null,
    null,
    null,
    this.cid,
    this.item_name,
    this.partner_order_id,
    this.partner_user_id,
    null,
    this.quantity.toInt(),
    null,
    PaymentStatus.READY,
    user,
    id
)

fun Payment.toDto() = PaymentDto(
    this.aid,
    this.amount?.toVo(),
    this.approvedAt,
    this.cid,
    this.createdAt,
    this.itemName,
    this.partnerOrderId,
    this.partnerUserId,
    this.paymentMethodType,
    this.quantity,
    this.tid,
    this.paymentStatus,
    this.user.toDto(),
)

fun KakaoRestApiApproveResponse.toEntity(user: User, id: Long?) = Payment.of(
    this.aid,
    Amount.of(
        this.amount.discount,
        this.amount.point,
        this.amount.taxFree,
        this.amount.total,
        this.amount.vat,
        this.amount.id
    ),
    this.approved_at,
    this.cid,
    this.item_name,
    this.partner_order_id,
    this.partner_user_id,
    this.payment_method_type,
    this.quantity.toInt(),
    this.tid,
    PaymentStatus.APPROVED,
    user,
    id
)
