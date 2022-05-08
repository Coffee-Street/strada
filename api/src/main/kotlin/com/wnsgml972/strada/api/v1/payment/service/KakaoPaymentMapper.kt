package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.account.service.toDto
import com.wnsgml972.strada.api.v1.payment.domain.Payment
import com.wnsgml972.strada.exception.StradaBadRequestException

fun PaymentApproveRequest.toEntity(user: User, id: Long? = null) = Payment.of(
    this.aid,
    this.amountVo.toEntity(id),
    this.approvedAt,
    this.cid,
    this.itemName,
    this.partnerOrderId,
    this.partnerUserId,
    this.paymentMethodType,
    this.quantity,
    this.tid,
    PaymentStatus.APPROVED,
    user,
    id
)

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
    this.id ?: throw StradaBadRequestException("$id is not null"),
)
