package com.wnsgml972.strada.api.v1.option.syrup.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.Entity

@Entity
class SyrupOption private constructor(
    val syrupType: Int,
    val syrupCount: Int,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is SyrupOption &&
                id == other.id &&
                syrupType == other.syrupType &&
                syrupCount == other.syrupCount
    }

    companion object {
        fun of(syrupType: Int, syrupCount: Int, id: Long?) =
            SyrupOption(syrupType, syrupCount, id)
    }
}
