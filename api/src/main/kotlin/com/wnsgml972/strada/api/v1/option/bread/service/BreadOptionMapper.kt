package com.wnsgml972.strada.api.v1.option.bread.service

import com.wnsgml972.strada.api.v1.option.bread.domain.BreadOption
import com.wnsgml972.strada.api.v1.ordering.service.toDto
import com.wnsgml972.strada.api.v1.ordering.service.toEntity
import com.wnsgml972.strada.exception.BadRequestException

fun BreadOption.toDto() = BreadOptionDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.orderingDetail.toDto(),
    this.hereOrToGo,
    this.forkCount
)

fun BreadOptionDTO.toEntity() = BreadOption.of(
    this.orderingDetail.toEntity(),
    this.hereOrToGo,
    this.forkCount,
    this.id
)
