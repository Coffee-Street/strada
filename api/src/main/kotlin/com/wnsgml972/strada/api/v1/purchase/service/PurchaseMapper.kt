package com.wnsgml972.strada.api.v1.purchase.service

import com.wnsgml972.strada.api.v1.purchase.domain.Purchase
import com.wnsgml972.strada.exception.StradaBadRequestException

fun Purchase.toDto() = PurchaseDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.userId,
    this.orderingId,
)