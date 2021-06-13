package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.Bean
import com.wnsgml972.strada.exception.BadRequestException

fun Bean.toDto() = BeanDTO(
    this.id ?: throw BadRequestException("$id must not null"),
    this.origin,
    this.farm,
    this.description,
    this.processing,
    this.roasting,
    this.kind,
    this.grade
)

fun BeanDTO.toEntity() = this.id?.let {
    Bean(
        it,
        this.origin,
        this.farm,
        this.description,
        this.processing,
        this.roasting,
        this.kind,
        this.grade
    )
}
