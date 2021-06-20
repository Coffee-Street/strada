package com.wnsgml972.strada.api.v1.option.drink.service

import com.wnsgml972.strada.api.v1.ordering.service.OrderingDetailDTO

data class DrinkOptionDTO(
    val id: Long,
    val orderingDetail: OrderingDetailDTO,
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
    val shotCount: Int
) {
    constructor(
        id: Long?,
        orderingDetail: OrderingDetailDTO,
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
        shotCount: Int
    ) : this(
        id ?: 0,
        orderingDetail,
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
        shotCount
    )
}
