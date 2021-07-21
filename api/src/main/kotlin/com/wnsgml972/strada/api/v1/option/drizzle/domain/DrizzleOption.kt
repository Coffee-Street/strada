package com.wnsgml972.strada.api.v1.option.drizzle.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.drizzle.service.DrizzleType

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class DrizzleOption private constructor(
    @Enumerated(EnumType.STRING)
    val drizzleType: DrizzleType,

    val drizzleCount: Int,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is DrizzleOption &&
                id == other.id &&
                drizzleType == other.drizzleType &&
                drizzleCount == other.drizzleCount
    }

    companion object {
        fun of(drizzleType: DrizzleType, drizzleCount: Int, id: Long?) =
            DrizzleOption(drizzleType, drizzleCount, id)
    }
}
