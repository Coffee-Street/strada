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
fun BannerInsertRequest.toEntity(code: String) = Banner(
    0,
    code,
    this.evalOrder,
    this.title,
    this.imageUrl,
    this.message,
)

fun BannerInsertRequest.toEntity(id: Long, code: String) = Banner(
    id,
    code,
    this.evalOrder,
    this.title,
    this.imageUrl,
    this.message,
)
