package com.wnsgml972.strada.api.v1.product.bean.service

import com.wnsgml972.strada.api.v1.product.bean.domain.Bean
import com.wnsgml972.strada.exception.StradaBadRequestException

fun Bean.toDto() = BeanDTO(
    this.id ?: throw StradaBadRequestException("$id must not null"),
    this.origin,
    this.farm,
    this.description,
    this.processing,
    this.roasting,
    this.kind,
    this.grade
)

fun BeanDTO.toEntity() =
    Bean(
        this.id,
        this.origin,
        this.farm,
        this.description,
        this.processing,
        this.roasting,
        this.kind,
        this.grade
    )
fun BeanInsertRequest.toBeanDto(id: String) =
    BeanDTO(
        id,
        this.origin,
        this.farm,
        this.description,
        this.processing,
        this.roasting,
        this.kind,
        this.grade
    )
