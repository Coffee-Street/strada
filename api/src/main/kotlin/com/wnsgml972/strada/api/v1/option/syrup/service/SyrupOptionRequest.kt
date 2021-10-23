package com.wnsgml972.strada.api.v1.option.syrup.service

import javax.validation.constraints.PositiveOrZero

data class SyrupOptionRequest(
    val syrupType: Int,

    @PositiveOrZero
    val syrupCount: Int,
)
