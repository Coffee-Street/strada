package com.wnsgml972.strada.api.v1.option.bean.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.bean.service.GrindType

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class BeanOption private constructor(
    @Enumerated(EnumType.STRING)
    val grindType: GrindType,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is BeanOption &&
                id == other.id &&
                grindType == other.grindType
    }

    companion object {
        fun of(grindType: GrindType, id: Long?) =
            BeanOption(grindType, id)
    }
}
