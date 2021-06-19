package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.option.drizzle.service.toDto
import com.wnsgml972.strada.api.v1.option.drizzle.service.toEntity
import com.wnsgml972.strada.api.v1.option.syrup.service.toDto
import com.wnsgml972.strada.api.v1.option.syrup.service.toEntity
import com.wnsgml972.strada.exception.BadRequestException

fun DrinkOption.toDto() = DrinkOptionDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.hotOrIced,
    this.cupType,
    this.cupSizeType,
    this.waterType,
    this.milkType,
    this.icedOnlyType,
    this.hotOnlyType,
    this.creamType,
    this.memoType,
    this.memo,
    this.shotCount,
    this.syrupOptions.map { it.toDto() }.toList(),
    this.drizzleOptions.map { it.toDto() }.toList()
)

fun DrinkOptionDTO.toEntity() = DrinkOption.of(
    this.hotOrIced,
    this.cupType,
    this.cupSizeType,
    this.waterType,
    this.milkType,
    this.icedOnlyType,
    this.hotOnlyType,
    this.creamType,
    this.memoType,
    this.memo,
    this.shotCount,
    this.syrupOptions.map { it.toEntity() }.toList(),
    this.drizzleOptions.map { it.toEntity() }.toList(),
    this.id
)