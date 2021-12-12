package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.ordering.domain.Ordering
import com.wnsgml972.strada.exception.StradaBadRequestException

fun Ordering.toDto() = OrderingDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.status,
    this.createdAt,
    this.orderingDetails.map { it.toDto() }
)
