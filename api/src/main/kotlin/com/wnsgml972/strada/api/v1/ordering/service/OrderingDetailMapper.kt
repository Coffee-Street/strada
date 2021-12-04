package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.option.bean.service.toDto
import com.wnsgml972.strada.api.v1.option.bean.service.toEntity
import com.wnsgml972.strada.api.v1.option.bread.service.toDto
import com.wnsgml972.strada.api.v1.option.bread.service.toEntity
import com.wnsgml972.strada.api.v1.option.drink.service.toDto
import com.wnsgml972.strada.api.v1.option.drink.service.toEntity
import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetail
import com.wnsgml972.strada.api.v1.product.bean.domain.Bean
import com.wnsgml972.strada.api.v1.product.bean.service.toDto
import com.wnsgml972.strada.api.v1.product.bread.domain.Bread
import com.wnsgml972.strada.api.v1.product.bread.service.toDto
import com.wnsgml972.strada.api.v1.product.coffee.domain.Coffee
import com.wnsgml972.strada.api.v1.product.coffee.service.toDto
import com.wnsgml972.strada.api.v1.product.noncoffee.domain.NonCoffee
import com.wnsgml972.strada.api.v1.product.noncoffee.service.toDto
import com.wnsgml972.strada.exception.StradaBadRequestException

fun OrderingDetail.toDto() = OrderingDetailDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.coffee?.toDto(),
    this.nonCoffee?.toDto(),
    this.bread?.toDto(),
    this.bean?.toDto(),
    this.drinkOption?.toDto(),
    this.breadOption?.toDto(),
    this.beanOption?.toDto()
)

fun OrderingDetailRequest.toEntity(
    coffee: Coffee? = null,
    nonCoffee: NonCoffee? = null,
    bread: Bread? = null,
    bean: Bean? = null,
    id: Long? = null,
) = OrderingDetail.of(
    coffee,
    nonCoffee,
    bread,
    bean,
    this.drinkOptionRequest?.toEntity(),
    this.breadOptionRequest?.toEntity(),
    this.beanOptionRequest?.toEntity(),
    id,
)
