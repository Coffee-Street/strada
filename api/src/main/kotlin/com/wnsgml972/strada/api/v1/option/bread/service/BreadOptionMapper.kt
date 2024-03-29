package com.wnsgml972.strada.api.v1.option.bread.service

import com.wnsgml972.strada.api.v1.option.bread.domain.BreadOption
import com.wnsgml972.strada.exception.StradaBadRequestException

fun BreadOption.toDto() = BreadOptionDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.hereOrToGo,
    this.forkCount
)

fun BreadOptionRequest.toEntity(id: Long? = null) = BreadOption.of(
    this.hereOrToGo,
    this.forkCount,
    id,
)
