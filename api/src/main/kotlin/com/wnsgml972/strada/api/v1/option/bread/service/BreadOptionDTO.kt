package com.wnsgml972.strada.api.v1.option.bread.service

data class BreadOptionDTO(
    val id: Long,
    val hereOrToGo: HereOrToGo,
    val forkCount: Int,
) {
    constructor(id: Long?, hereOrToGo: HereOrToGo, forkCount: Int) : this(
    id ?: 0,
    hereOrToGo,
    forkCount
    )
}
