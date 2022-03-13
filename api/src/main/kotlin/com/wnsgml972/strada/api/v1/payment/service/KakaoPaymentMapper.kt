package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.payment.domain.Payment
import com.wnsgml972.strada.exception.StradaBadRequestException

fun PaymentInsertRequest.toEntity(user: User, id: Long? = null) = Payment.of(
    this.aid,
    this.amountVo.toEntity(),
    this.approvedAt,
    this.cid,
    this.createdAt,
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

fun Payment.toDto() = PaymentDto(
    this.aid,
    this.amount.toVo(),
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
    this.user,
    this.id ?: throw StradaBadRequestException("$id is not null"),
)
