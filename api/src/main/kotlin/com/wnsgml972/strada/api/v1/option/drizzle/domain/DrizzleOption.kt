package com.wnsgml972.strada.api.v1.option.drizzle.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleType
import javax.persistence.CascadeType

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

@Entity
class DrizzleOption private constructor(
    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val drinkOption: DrinkOption,

    @Enumerated(EnumType.STRING)
    val drizzleType: DrizzleType,

    val drizzleCount: Int,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is DrizzleOption &&
                id == other.id &&
                drinkOption == other.drinkOption &&
                drizzleType == other.drizzleType &&
                drizzleCount == other.drizzleCount
    }

    companion object {
        fun of(drinkOption: DrinkOption, drizzleType: DrizzleType, drizzleCount: Int, id: Long?) =
            DrizzleOption(drinkOption, drizzleType, drizzleCount, id)
    }
}
