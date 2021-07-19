package com.wnsgml972.strada.api.v1.item.bread.service

data class BreadDTO(

    val id: String,
    val url: String,
    val price: Int,
    val description: String,
    val category: String,
) {
    constructor(id: String, breadInsertRequest: BreadInsertRequest) : this(
        id,
        breadInsertRequest.url,
        breadInsertRequest.price,
        breadInsertRequest.description,
        breadInsertRequest.category,
    )
}
