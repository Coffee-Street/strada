package com.wnsgml972.strada.api.v1.option.drink.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.drink.service.CupType
import com.wnsgml972.strada.api.v1.option.drink.service.HotOrIcedType
import com.wnsgml972.strada.api.v1.option.drink.service.QuantityType
import com.wnsgml972.strada.api.v1.option.drizzle.domain.DrizzleOption
import com.wnsgml972.strada.api.v1.option.syrup.domain.SyrupOption
import javax.persistence.CascadeType

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

/**
 * DrinkOption
 * 음료를 주문할 때 들어가는 옵션들
 *
 * @property hotOrIced
 * @property cupType
 * @property cupSize 1이 가장 작은 컵이며, 커질수록 큰 컵을 의미함
 * @property water 수량 단위로 처리
 * @property milk 수량 단위로 처리
 * @property iced 수량 단위로 처리
 * @property hot 수량 단위로 처리
 * @property cream 수량 단위로 처리
 *
 */

@Entity
@SuppressWarnings("LongParameterList", "ComplexMethod")
class DrinkOption private constructor(
    @Enumerated(EnumType.STRING)
    val hotOrIced: HotOrIcedType,

    @Enumerated(EnumType.STRING)
    val cupType: CupType,

    val cupSize: Int,

    val water: QuantityType,

    val milk: QuantityType,

    val iced: QuantityType,

    val hot: QuantityType,

    val cream: QuantityType,

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
            water: QuantityType,
            milk: QuantityType,
            iced: QuantityType,
            hot: QuantityType,
            cream: QuantityType,
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
