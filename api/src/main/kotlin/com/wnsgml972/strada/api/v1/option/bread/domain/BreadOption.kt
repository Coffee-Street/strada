package com.wnsgml972.strada.api.v1.option.bread.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.bread.service.HereOrToGo
import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetail
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToOne

@Entity
class BreadOption private constructor(
    @OneToOne
    val orderingDetail: OrderingDetail,

    @Enumerated(EnumType.STRING)
    val hereOrToGo: HereOrToGo,

    val forkCount: Int,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is BreadOption &&
                id == other.id &&
                orderingDetail == other.orderingDetail &&
                hereOrToGo == other.hereOrToGo &&
                forkCount == other.forkCount
    }

    companion object {
        fun of(orderingDetail: OrderingDetail, hereOrToGo: HereOrToGo, forkCount: Int, id: Long?) =
            BreadOption(orderingDetail, hereOrToGo, forkCount, id)
    }
}
