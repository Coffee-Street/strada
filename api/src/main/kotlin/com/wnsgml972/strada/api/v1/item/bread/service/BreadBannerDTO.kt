package com.wnsgml972.strada.api.v1.item.bread.service

/**
 * BreadBannerDTO
 *
 * Banner에서 bread에 대한 정보를 요청할 때 사용하는 DTO 클래
 * Tag domain을 위해 차후 tags 리스트를 추가한다
 *
 */
data class BreadBannerDTO(
    val id: String,
    val imageUrl: String,
    val description: String,
)
