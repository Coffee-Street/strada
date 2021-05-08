package com.wnsgml972.strada.api.v1.option.drink.service

data class DrinkOptionDTO(
    val id: Long,
    val hotOrIced: Int,
    val cupType: Int,
    val sizeType: Int,
    val waterType: Int,
    val milkType: Int,
    val icedOnlyType: Int,
    val hotOnlyType: Int,
    val creamType: Int,
    val memoType: Int,
    val memo: String,
)
