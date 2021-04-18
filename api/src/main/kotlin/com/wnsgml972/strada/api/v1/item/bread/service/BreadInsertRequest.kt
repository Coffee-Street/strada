package com.wnsgml972.strada.api.v1.item.bread.service

data class BreadInsertRequest(
    val url: String,
    val price: Int,
    val description: String,
    val category: String,
)
