package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleOptionRequest
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupOptionRequest
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

data class DrinkOptionRequest(
    val hotOrIced: HotOrIcedType,
    val cupType: CupType,

    @Positive
    val cupSize: Int,
    val water: QuantityType,
    val milk: QuantityType,
    val iced: QuantityType,
    val hot: QuantityType,
    val cream: QuantityType,
    val memo: String,

    @PositiveOrZero
    val shotCount: Int,

    val syrupOptionRequests: List<SyrupOptionRequest>,
    val drizzleOptionRequests: List<DrizzleOptionRequest>
)
