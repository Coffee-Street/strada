package com.wnsgml972.strada.api.v1.option.drink.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.drink.service.HotOrIcedType
import com.wnsgml972.strada.api.v1.option.drink.service.CupType
import com.wnsgml972.strada.api.v1.option.drink.service.CupSizeType
import com.wnsgml972.strada.api.v1.option.drink.service.WaterType
import com.wnsgml972.strada.api.v1.option.drink.service.MilkType
import com.wnsgml972.strada.api.v1.option.drink.service.IcedOnlyType
import com.wnsgml972.strada.api.v1.option.drink.service.HotOnlyType
import com.wnsgml972.strada.api.v1.option.drink.service.CreamType
import com.wnsgml972.strada.api.v1.option.drink.service.MemoType
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

    @Enumerated(EnumType.STRING)
    val cupSizeType: CupSizeType,

    @Enumerated(EnumType.STRING)
    val waterType: WaterType,

    @Enumerated(EnumType.STRING)
    val milkType: MilkType,

    @Enumerated(EnumType.STRING)
    val icedOnlyType: IcedOnlyType,

    @Enumerated(EnumType.STRING)
    val hotOnlyType: HotOnlyType,

    @Enumerated(EnumType.STRING)
    val creamType: CreamType,

    @Enumerated(EnumType.STRING)
    val memoType: MemoType,

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
                cupSizeType == other.cupSizeType &&
                waterType == other.waterType &&
                milkType == other.milkType &&
                icedOnlyType == other.icedOnlyType &&
                hotOnlyType == other.hotOnlyType &&
                creamType == other.creamType &&
                memoType == other.memoType &&
                memo == other.memo &&
                shotCount == other.shotCount &&
                syrupOptions == other.syrupOptions &&
                drizzleOptions == other.drizzleOptions
    }

    companion object {
        fun of(
            hotOrIcedType: HotOrIcedType,
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
            syrupOptions: List<SyrupOption>,
            drizzleOptions: List<DrizzleOption>,
            id: Long?
        ) =
            DrinkOption(
                hotOrIcedType,
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
                drizzleOptions,
                id
            )
    }
}
