package com.wnsgml972.strada.api.v1.option.bread.service

import javax.validation.constraints.PositiveOrZero

data class BreadOptionRequest(
    val hereOrToGo: HereOrToGo,

    @PositiveOrZero
    val forkCount: Int,
)
