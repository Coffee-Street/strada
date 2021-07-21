package com.wnsgml972.strada.api.v1.option.syrup.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.syrup.service.SyrupType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class SyrupOption private constructor(
    @Enumerated(EnumType.STRING)
    val syrupType: SyrupType,

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
        fun of(syrupType: SyrupType, syrupCount: Int, id: Long?) =
            SyrupOption(syrupType, syrupCount, id)
    }
}
