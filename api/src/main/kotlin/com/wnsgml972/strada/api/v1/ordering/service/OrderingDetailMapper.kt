package com.wnsgml972.strada.api.v1.ordering.service

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
    this.ordering.toDto(),
    this.drinkOption?.toDto(),
    this.breadOption?.toDto(),
    this.beanOption?.toDto()
)

fun OrderingDetailDTO.toEntity() = OrderingDetail.of(
    this.ordering.toEntity(),
    this.drinkOption?.toEntity(),
    this.breadOption?.toEntity(),
    this.beanOption?.toEntity(),
    this.id,
)

fun OrderingDetailRequest.toDto(orderingDTO: OrderingDTO, id: Long = 0) = OrderingDetailDTO(
    id,
    orderingDTO,
    this.drinkOptionDTO,
    this.breadOptionDTO,
    this.beanOptionDTO
)
