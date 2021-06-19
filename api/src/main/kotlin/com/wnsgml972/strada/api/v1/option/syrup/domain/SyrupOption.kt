package com.wnsgml972.strada.api.v1.option.syrup.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupType
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

@Entity
class SyrupOption private constructor(
    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val drinkOption: DrinkOption,

    @Enumerated(EnumType.STRING)
    val syrupType: SyrupType,

    val syrupCount: Int,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is SyrupOption &&
                id == other.id &&
                drinkOption == other.drinkOption &&
                syrupType == other.syrupType &&
                syrupCount == other.syrupCount
    }

    companion object {
        fun of(drinkOption: DrinkOption, syrupType: SyrupType, syrupCount: Int, id: Long?) =
            SyrupOption(drinkOption, syrupType, syrupCount, id)
    }
}
