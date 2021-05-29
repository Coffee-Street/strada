package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.Bean

fun Bean.toDto() = BeanDTO(
    this.id,
    this.origin,
    this.farm,
    this.description,
    this.processing,
    this.roasting,
    this.kind,
    this.grade
)

fun BeanDTO.toEntity() = Bean(
    this.id,
    this.origin,
    this.farm,
    this.description,
    this.processing,
    this.roasting,
    this.kind,
    this.grade
)