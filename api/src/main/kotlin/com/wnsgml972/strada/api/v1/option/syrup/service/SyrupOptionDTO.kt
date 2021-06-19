package com.wnsgml972.strada.api.v1.option.syrup.service

import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO

data class SyrupOptionDTO(
    val id: Long,
    val drinkOption: DrinkOptionDTO,
    val syrupType: SyrupType,
    val syrupCount: Int,
) {
    constructor(drinkOption: DrinkOptionDTO, syrupType: SyrupType, syrupCount: Int, id: Long?) : this(
        id ?: 0,
        drinkOption,
        syrupType,
        syrupCount
    )
}
