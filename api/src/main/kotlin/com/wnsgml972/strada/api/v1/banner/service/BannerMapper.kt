package com.wnsgml972.strada.api.v1.banner.service

import com.wnsgml972.strada.api.v1.banner.domain.Banner

fun Banner.toDto() = BannerDTO(
    this.code,
    this.title,
    this.imageUrl,
    this.message,
)

fun Banner.toBannerInsertResponse() = BannerInsertResponse(
    this.evalOrder,
    this.code,
    this.title,
    this.imageUrl,
    this.message,
)
fun BannerInsertRequest.toEntity() = Banner(
    0,
    this.code,
    this.evalOrder,
    this.title,
    this.imageUrl,
    this.message,
)

fun BannerInsertRequest.toEntity(id: Long) = Banner(
    id,
    this.code,
    this.evalOrder,
    this.title,
    this.imageUrl,
    this.message,
)
