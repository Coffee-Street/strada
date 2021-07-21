package com.wnsgml972.strada.api.v1.option.bean.service

data class BeanOptionDTO(
    val id: Long,
    val grindType: GrindType,
) {
    constructor(id: Long?, grindType: GrindType) : this(
        id ?: 0,
        grindType
    )
}
