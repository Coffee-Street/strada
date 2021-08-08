package com.wnsgml972.strada.api.v1.option.drink.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.drink.service.CupType
import com.wnsgml972.strada.api.v1.option.drink.service.HotOrIcedType
import com.wnsgml972.strada.api.v1.option.drizzle.domain.DrizzleOption
import com.wnsgml972.strada.api.v1.option.syrup.domain.SyrupOption
import javax.persistence.CascadeType

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
@SuppressWarnings("LongParameterList", "ComplexMethod")
class DrinkOption private constructor(
    @Enumerated(EnumType.STRING)
    val hotOrIced: HotOrIcedType,

    @Enumerated(EnumType.STRING)
    val cupType: CupType,

    val cupSize: Int,

    val water: Int,

    val milk: Int,

    val iced: Int,

    val hot: Int,

    val cream: Int,

    val memo: String = "",

    val shotCount: Int,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "drinkOption_id")
    val syrupOptions: List<SyrupOption>,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "drinkOption_id")
    val drizzleOptions: List<DrizzleOption>,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is DrinkOption &&
                id == other.id &&
                hotOrIced == other.hotOrIced &&
                cupType == other.cupType &&
                cupSize == other.cupSize &&
                water == other.water &&
                milk == other.milk &&
                iced == other.iced &&
                hot == other.hot &&
                cream == other.cream &&
                memo == other.memo &&
                shotCount == other.shotCount &&
                syrupOptions == other.syrupOptions &&
                drizzleOptions == other.drizzleOptions
    }

    companion object {
        fun of(
            hotOrIcedType: HotOrIcedType,
            cupType: CupType,
            cupSize: Int,
            water: Int,
            milk: Int,
            iced: Int,
            hot: Int,
            cream: Int,
            memo: String,
            shotCount: Int,
            syrupOptions: List<SyrupOption>,
            drizzleOptions: List<DrizzleOption>,
            id: Long?
        ) =
            DrinkOption(
                hotOrIcedType,
                cupType,
                cupSize,
                water,
                milk,
                iced,
                hot,
                cream,
                memo,
                shotCount,
                syrupOptions,
                drizzleOptions,
                id
            )
    }
}
