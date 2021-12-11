package com.wnsgml972.strada.api.v1.option.drizzle.service

import com.wnsgml972.strada.api.v1.option.drizzle.domain.DrizzleOption
import com.wnsgml972.strada.exception.StradaBadRequestException

fun DrizzleOption.toDto() = DrizzleOptionDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.drizzleType,
    this.drizzleCount
)

fun DrizzleOptionRequest.toEntity(id: Long? = null) = DrizzleOption.of(
    this.drizzleType,
    this.drizzleCount,
    id
)
