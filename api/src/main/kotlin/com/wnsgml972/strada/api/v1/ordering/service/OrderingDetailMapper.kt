package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.option.bean.service.toDto
import com.wnsgml972.strada.api.v1.option.bread.service.toDto
import com.wnsgml972.strada.api.v1.option.drink.service.toDto
import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetail
import com.wnsgml972.strada.exception.StradaBadRequestException

fun OrderingDetail.toDto() = OrderingDetailDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.coffee?.name,
    this.nonCoffee?.id,
    this.bread?.id,
    this.bean?.id,
    this.drinkOption?.toDto(),
    this.breadOption?.toDto(),
    this.beanOption?.toDto()
)

// fun OrderingDetailRequest.toEntity(id: Long? = null) = OrderingDetail.of(
//    this.coffeeName
//    this.nonCoffeeId,
//    this.breadId,
//    this.beanId,
//    this.drinkOptionRequest?.toEntity(),
//    this.breadOptionRequest?.toEntity(),
//    this.beanOptionRequest?.toEntity(),
//    id,
// )
