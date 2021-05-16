package com.wnsgml972.strada.api.v1.item.noncoffee.service

/**
 * NonCoffeeBannerDTO
 *
 * Banner에서 NonCoffee에 대한 정보를 요청할 때 사용하는 DTO 클래
 * Tag domain을 위해 차후 tags 리스트를 추가한다
 *
 */

data class NonCoffeeBannerDTO(

    val id: String,
    val url: String,
    val description: String,
    //val tags: List<Any>

)