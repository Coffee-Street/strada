package com.wnsgml972.strada.api.v1.banner.service

data class BannerInsertResponse(
    val evalOrder: Int,
    val code: String,
    val title: String,
    val imageUrl: String,
    val message: String
)