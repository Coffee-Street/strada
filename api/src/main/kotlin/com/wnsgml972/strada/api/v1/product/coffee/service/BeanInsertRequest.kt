package com.wnsgml972.strada.api.v1.product.coffee.service

import javax.validation.constraints.Size

data class BeanInsertRequest(

    @field:Size(max = 128, message = "128자 제한입니다.")
    val origin: String,

    @field:Size(max = 128, message = "128자 제한입니다.")
    val farm: String,

    @field:Size(max = 128, message = "128자 제한입니다.")
    val description: String,

    @field:Size(max = 128, message = "128자 제한입니다.")
    val processing: String,

    @field:Size(max = 128, message = "128자 제한입니다.")
    val roasting: String,

    @field:Size(max = 128, message = "128자 제한입니다.")
    val kind: String,

    @field:Size(max = 128, message = "128자 제한입니다.")
    val grade: String,
)
