package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.ordering.service.toDto
import com.wnsgml972.strada.api.v1.ordering.service.toEntity
import com.wnsgml972.strada.exception.BadRequestException

fun DrinkOption.toDto() = DrinkOptionDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.orderingDetail.toDto(),
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
)

fun DrinkOptionDTO.toEntity() = DrinkOption.of(
    this.orderingDetail.toEntity(),
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
    this.id
)
