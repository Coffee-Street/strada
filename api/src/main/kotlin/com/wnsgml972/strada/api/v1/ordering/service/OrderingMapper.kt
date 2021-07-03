package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.ordering.domain.Ordering
import com.wnsgml972.strada.exception.BadRequestException
import java.time.LocalDateTime

fun Ordering.toDto() = OrderingDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.status,
    this.createdAt
)

fun OrderingDTO.toEntity() = Ordering.of(
    this.status,
    this.createdAt,
    this.id,
)

fun OrderingRequest.toDto(id: Long = 0) = OrderingDTO(
    id,
    status,
    LocalDateTime.now()
)
