package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleOptionDTO
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupOptionDTO
import javax.validation.constraints.PositiveOrZero

data class DrinkOptionDTO(
    val id: Long,
    val hotOrIced: HotOrIcedType,
    val cupType: CupType,
    val cupSize: Int,
    val water: QuantityType,
    val milk: QuantityType,
    val iced: QuantityType,
    val hot: QuantityType,
    val cream: QuantityType,
    val memo: String,

    @PositiveOrZero
    val shotCount: Int,

    val syrupOptions: List<SyrupOptionDTO>,
    val drizzleOptions: List<DrizzleOptionDTO>
) {
    constructor(
        id: Long?,
        hotOrIced: HotOrIcedType,
        cupType: CupType,
        cupSizeType: Int,
        water: QuantityType,
        milk: QuantityType,
        iced: QuantityType,
        hot: QuantityType,
        cream: QuantityType,
        memo: String,
        shotCount: Int,
        syrupOptions: List<SyrupOptionDTO>,
        drizzleOptions: List<DrizzleOptionDTO>
    ) : this(
        id ?: -1,
        hotOrIced,
        cupType,
        cupSizeType,
        water,
        milk,
        iced,
        hot,
        cream,
        memo,
        shotCount,
        syrupOptions,
        drizzleOptions
    )
}
