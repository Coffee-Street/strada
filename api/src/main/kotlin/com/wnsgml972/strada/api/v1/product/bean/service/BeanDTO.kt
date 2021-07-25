package com.wnsgml972.strada.api.v1.product.bean.service

data class BeanDTO(
    val id: String?,
    val origin: String,
    val farm: String,
    val description: String,
    val processing: String,
    val roasting: String,
    val kind: String,
    val grade: String,
) {
    constructor(id: String, beanInsertRequest: BeanInsertRequest) : this(
        id,
        beanInsertRequest.origin,
        beanInsertRequest.farm,
        beanInsertRequest.description,
        beanInsertRequest.processing,
        beanInsertRequest.roasting,
        beanInsertRequest.kind,
        beanInsertRequest.grade
    )
}
