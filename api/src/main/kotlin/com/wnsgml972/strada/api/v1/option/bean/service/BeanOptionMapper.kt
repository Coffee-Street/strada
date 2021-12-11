package com.wnsgml972.strada.api.v1.option.bean.service

import com.wnsgml972.strada.api.v1.option.bean.domain.BeanOption
import com.wnsgml972.strada.exception.StradaBadRequestException

fun BeanOption.toDto() = BeanOptionDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.grindType
)

fun BeanOptionRequest.toEntity(id: Long? = null) = BeanOption.of(
    this.grindType,
    id
)
