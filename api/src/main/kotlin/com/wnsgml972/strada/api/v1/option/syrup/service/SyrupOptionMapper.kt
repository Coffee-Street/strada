package com.wnsgml972.strada.api.v1.option.syrup.service

import com.wnsgml972.strada.api.v1.option.drink.service.toDto
import com.wnsgml972.strada.api.v1.option.drink.service.toEntity
import com.wnsgml972.strada.api.v1.option.syrup.domain.SyrupOption
import com.wnsgml972.strada.exception.BadRequestException

fun SyrupOption.toDto() = SyrupOptionDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.drinkOption.toDto(),
    this.syrupType,
    this.syrupCount
)

fun SyrupOptionDTO.toEntity() = SyrupOption.of(
    this.drinkOption.toEntity(),
    this.syrupType,
    this.syrupCount,
    this.id
)
