package com.wnsgml972.strada.api.v1.option.drizzle.service

import javax.validation.constraints.PositiveOrZero

data class DrizzleOptionRequest(
    val drizzleType: Int,

    @PositiveOrZero
    val drizzleCount: Int,
)
