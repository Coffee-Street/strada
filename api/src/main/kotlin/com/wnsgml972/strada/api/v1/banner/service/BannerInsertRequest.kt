package com.wnsgml972.strada.api.v1.banner.service

import org.hibernate.validator.constraints.URL
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class BannerInsertRequest(

    @field:Positive
    val evalOrder: Int,

    @field:Size(max = 255, message = "최대 255글자까지 입력가능합니다.")
    val title: String,

    @field:URL(message = "imageUrl형식입니다.")
    val imageUrl: String,

    @field:Size(max = 255, message = "최대 255글자까지 입력가능합니다.")
    val message: String

)
