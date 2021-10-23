package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.option.drizzle.service.toDto
import com.wnsgml972.strada.api.v1.option.drizzle.service.toEntity
import com.wnsgml972.strada.api.v1.option.syrup.service.toDto
import com.wnsgml972.strada.api.v1.option.syrup.service.toEntity
import com.wnsgml972.strada.exception.StradaBadRequestException

fun DrinkOption.toDto() = DrinkOptionDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.hotOrIced,
    this.cupType,
    this.cupSize,
    this.water,
    this.milk,
    this.iced,
    this.hot,
    this.cream,
    this.memo,
    this.shotCount,
    this.syrupOptions.map { it.toDto() },
    this.drizzleOptions.map { it.toDto() }
)

fun DrinkOptionDTO.toEntity() = DrinkOption.of(
    this.hotOrIced,
    this.cupType,
    this.cupSize,
    this.water,
    this.milk,
    this.iced,
    this.hot,
    this.cream,
    this.memo,
    this.shotCount,
    this.syrupOptions.map { it.toEntity() },
    this.drizzleOptions.map { it.toEntity() },
    this.id
)
