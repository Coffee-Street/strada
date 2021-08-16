package com.wnsgml972.strada.api.v1.item.bread.service

import org.hibernate.validator.constraints.URL
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class BreadInsertRequest(
    @field:URL(message = "URL형식입니다.")
    val imageUrl: String,

    @field:Positive(message = "금액을 입력해 주세요.")
    @NotNull
    val price: Int,

    @field:Size(max = 255, message = "최대 255글자까지 입력가능합니다.")
    val description: String,

    @field:Size(max = 20, message = "20자 제한입니다.")
    val category: String,
)
