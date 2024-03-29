package com.wnsgml972.strada.api.v1.option.syrup.service

import com.wnsgml972.strada.api.v1.option.syrup.domain.SyrupOption
import com.wnsgml972.strada.exception.StradaBadRequestException

fun SyrupOption.toDto() = SyrupOptionDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.syrupType,
    this.syrupCount
)

fun SyrupOptionRequest.toEntity(id: Long? = null) = SyrupOption.of(
    this.syrupType,
    this.syrupCount,
    id
)
