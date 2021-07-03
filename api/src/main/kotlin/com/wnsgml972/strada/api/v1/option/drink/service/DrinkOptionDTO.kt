package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleOptionDTO
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupOptionDTO

data class DrinkOptionDTO(
    val id: Long,
    val hotOrIced: HotOrIcedType,
    val cupType: CupType,
    val cupSizeType: CupSizeType,
    val waterType: WaterType,
    val milkType: MilkType,
    val icedOnlyType: IcedOnlyType,
    val hotOnlyType: HotOnlyType,
    val creamType: CreamType,
    val memoType: MemoType,
    val memo: String,
    val shotCount: Int,
    val syrupOptions: List<SyrupOptionDTO>,
    val drizzleOptions: List<DrizzleOptionDTO>
) {
    constructor(
        id: Long?,
        hotOrIced: HotOrIcedType,
        cupType: CupType,
        cupSizeType: CupSizeType,
        waterType: WaterType,
        milkType: MilkType,
        icedOnlyType: IcedOnlyType,
        hotOnlyType: HotOnlyType,
        creamType: CreamType,
        memoType: MemoType,
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
        memoType,
        memo,
        shotCount,
        syrupOptions,
        drizzleOptions
    )
}
