package com.wnsgml972.strada.api.v1.banner.service

data class BannerDTO(

    val evalOrder: Int,
    val topLatters: String,
    val imageUrl: String,
    val bottomLatters: String
) {

    constructor(bannerInsertRequest: BannerInsertRequest) : this(
        bannerInsertRequest.evalOrder,
        bannerInsertRequest.topLatters,
        bannerInsertRequest.imageUrl,
        bannerInsertRequest.bottomLatters,
    )
}