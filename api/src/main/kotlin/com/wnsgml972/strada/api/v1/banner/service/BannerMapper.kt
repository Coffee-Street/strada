package com.wnsgml972.strada.api.v1.banner.service

import com.wnsgml972.strada.api.v1.banner.domain.Banner

fun Banner.toDto() = BannerDTO(
    this.evalOrder,
    this.topLatters,
    this.imageUrl,
    this.bottomLatters
)

fun BannerDTO.toEntity() = Banner(
    0,
    this.evalOrder,
    this.topLatters,
    this.imageUrl,
    this.bottomLatters
)

fun BannerDTO.toEntity(id: Long) = Banner(
    id,
    this.evalOrder,
    this.topLatters,
    this.imageUrl,
    this.bottomLatters
)
