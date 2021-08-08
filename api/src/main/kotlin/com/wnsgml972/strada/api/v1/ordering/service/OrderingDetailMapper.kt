package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.item.bread.service.toDto
import com.wnsgml972.strada.api.v1.item.bread.service.toEntity
import com.wnsgml972.strada.api.v1.item.coffee.service.toDto
import com.wnsgml972.strada.api.v1.item.coffee.service.toEntity
import com.wnsgml972.strada.api.v1.item.noncoffee.service.toDto
import com.wnsgml972.strada.api.v1.item.noncoffee.service.toEntity
import com.wnsgml972.strada.api.v1.option.bean.service.toDto
import com.wnsgml972.strada.api.v1.option.bean.service.toEntity
import com.wnsgml972.strada.api.v1.option.bread.service.toDto
import com.wnsgml972.strada.api.v1.option.bread.service.toEntity
import com.wnsgml972.strada.api.v1.option.drink.service.toDto
import com.wnsgml972.strada.api.v1.option.drink.service.toEntity
import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetail
import com.wnsgml972.strada.exception.BadRequestException

fun OrderingDetail.toDto() = OrderingDetailDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.coffee?.toDto(),
    this.nonCoffee?.toDto(),
    this.bread?.toDto(),
    this.bean?.toDto(),
    this.drinkOption?.toDto(),
    this.breadOption?.toDto(),
    this.beanOption?.toDto()
)

fun OrderingDetailDTO.toEntity() = OrderingDetail.of(
    this.coffee?.toEntity(),
    this.nonCoffee?.toEntity(),
    this.bread?.toEntity(),
    this.bean?.toEntity(),
    this.drinkOption?.toEntity(),
    this.breadOption?.toEntity(),
    this.beanOption?.toEntity(),
    this.id,
)

fun OrderingDetailRequest.toDto(id: Long = 0) = OrderingDetailDTO(
    id,
    this.coffeeDTO,
    this.nonCoffeeDTO,
    this.breadDTO,
    this.beanDTO,
    this.drinkOptionDTO,
    this.breadOptionDTO,
    this.beanOptionDTO
)
