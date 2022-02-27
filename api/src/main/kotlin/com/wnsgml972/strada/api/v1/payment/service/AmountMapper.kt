package com.wnsgml972.strada.api.v1.payment.service

import com.wnsgml972.strada.api.v1.payment.domain.Amount

fun AmountVo.toEntity(id: Long? = null) = Amount.of(
    this.discount,
    this.point,
    this.taxFree,
    this.total,
    this.vat,
    id
)

fun Amount.toVo() = AmountVo(
    this.discount,
    this.point,
    this.taxFree,
    this.total,
    this.vat,
)
