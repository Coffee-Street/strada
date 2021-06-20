package com.wnsgml972.strada.api.v1.option.drizzle.service

import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO

data class DrizzleOptionDTO(
    val id: Long,
    val drinkOption: DrinkOptionDTO,
    val drizzleType: DrizzleType,
    val drizzleCount: Int,
) {
    constructor(id: Long?, drinkOption: DrinkOptionDTO, drizzleType: DrizzleType, drizzleCount: Int) : this(
        id ?: 0,
        drinkOption,
        drizzleType,
        drizzleCount
    )
}
