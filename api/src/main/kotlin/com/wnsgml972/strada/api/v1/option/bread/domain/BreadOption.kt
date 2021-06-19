package com.wnsgml972.strada.api.v1.option.bread.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.bread.service.HereOrToGo
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class BreadOption private constructor(
    @Enumerated(EnumType.STRING)
    val hereOrToGo: HereOrToGo,

    val forkCount: Int,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is BreadOption &&
                id == other.id &&
                hereOrToGo == other.hereOrToGo &&
                forkCount == other.forkCount
    }

    companion object {
        fun of(hereOrToGo: HereOrToGo, forkCount: Int, id: Long?) =
            BreadOption(hereOrToGo, forkCount, id)
    }
}
