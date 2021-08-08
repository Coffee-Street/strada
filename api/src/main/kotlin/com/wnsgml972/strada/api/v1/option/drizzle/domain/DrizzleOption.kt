package com.wnsgml972.strada.api.v1.option.drizzle.domain

import com.wnsgml972.strada.api.base.LongJpaEntity

import javax.persistence.Entity

@Entity
class DrizzleOption private constructor(
    val drizzleType: Int,
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
        fun of(drizzleType: Int, drizzleCount: Int, id: Long?) =
            DrizzleOption(drizzleType, drizzleCount, id)
    }
}
