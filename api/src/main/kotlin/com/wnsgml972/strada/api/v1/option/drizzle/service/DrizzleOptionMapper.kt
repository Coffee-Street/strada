package com.wnsgml972.strada.api.v1.option.drizzle.service

import com.wnsgml972.strada.api.v1.option.drizzle.domain.DrizzleOption
import com.wnsgml972.strada.exception.BadRequestException

fun DrizzleOption.toDto() = DrizzleOptionDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.drizzleType,
    this.drizzleCount
)

fun DrizzleOptionDTO.toEntity() = DrizzleOption.of(
    this.drizzleType,
    this.drizzleCount,
    this.id
)
