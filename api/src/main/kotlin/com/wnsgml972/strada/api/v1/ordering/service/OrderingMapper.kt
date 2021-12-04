package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.ordering.domain.Ordering
import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetail
import com.wnsgml972.strada.exception.StradaBadRequestException

import java.time.LocalDateTime

fun Ordering.toDto() = OrderingDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.status,
    this.createdAt,
    this.orderingDetails.map { it.toDto() }
)

fun OrderingRequest.toEntity(id: Long? = null) = Ordering.of(
    this.status,
    LocalDateTime.now(),
    this.orderingDetailRequests.map { it.toEntity() },
    id,
)

fun OrderingRequest.toEntity(orderingDetail: List<OrderingDetail>, id: Long? = null) = Ordering.of(
    this.status,
    LocalDateTime.now(),
    orderingDetail,
    id,
)