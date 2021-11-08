package com.wnsgml972.strada.api.v1.banner.service

data class BannerDTO(
    val code: String,
    val title: String,
    val imageUrl: String,
    val message: String,
    val backgroundColor: String,
    val fontColor: String
)
