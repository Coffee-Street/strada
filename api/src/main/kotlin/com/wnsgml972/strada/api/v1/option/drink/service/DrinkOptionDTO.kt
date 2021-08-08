package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleOptionDTO
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupOptionDTO

data class DrinkOptionDTO(
    val id: Long,
    val hotOrIced: HotOrIcedType,
    val cupType: CupType,
    val cupSize: Int,
    val water: Int,
    val milk: Int,
    val iced: Int,
    val hot: Int,
    val cream: Int,
    val memo: String,
    val shotCount: Int,
    val syrupOptions: List<SyrupOptionDTO>,
    val drizzleOptions: List<DrizzleOptionDTO>
) {
    constructor(
        id: Long?,
        hotOrIced: HotOrIcedType,
        cupType: CupType,
        cupSizeType: Int,
        waterType: Int,
        milkType: Int,
        icedOnlyType: Int,
        hotOnlyType: Int,
        creamType: Int,
        memo: String,
        shotCount: Int,
        syrupOptions: List<SyrupOptionDTO>,
        drizzleOptions: List<DrizzleOptionDTO>
    ) : this(
        id ?: -1,
        hotOrIced,
        cupType,
        cupSizeType,
        waterType,
        milkType,
        icedOnlyType,
        hotOnlyType,
        creamType,
        memo,
        shotCount,
        syrupOptions,
        drizzleOptions
    )
}
