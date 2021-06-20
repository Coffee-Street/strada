package com.wnsgml972.strada.api.v1.option.bean.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.bean.service.GrindType
import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetail

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToOne

@Entity
class BeanOption private constructor(
    @OneToOne
    val orderingDetail: OrderingDetail,

    @Enumerated(EnumType.STRING)
    val grindType: GrindType,

    override var id: Long? = null,
) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is BeanOption &&
                id == other.id &&
                orderingDetail == other.orderingDetail &&
                grindType == other.grindType
    }

    companion object {
        fun of(orderingDetail: OrderingDetail, grindType: GrindType, id: Long?) =
            BeanOption(orderingDetail, grindType, id)
    }
}
