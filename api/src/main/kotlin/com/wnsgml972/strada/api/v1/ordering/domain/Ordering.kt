package com.wnsgml972.strada.api.v1.ordering.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.ordering.service.OrderingStatus
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.OneToMany

/**
 * Order
 *
 * 주문 정보의 최상위 Entity
 * table 이름은 order가 keyword라서 orders로 임시 명명 ( 복수형과 단수형에 대해서 의논 필요 )
 *
 * @property userID
 * @property status
 * @property createdAt
 * @property updatedAt
 * @property orderingDetails
 *
 */
@Entity
@SuppressWarnings("LongParameterList")
class Ordering private constructor(

    @Enumerated(EnumType.STRING)
    val status: OrderingStatus,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ordering", cascade = [CascadeType.ALL, CascadeType.PERSIST])
    val orderingDetails: List<OrderingDetail>,

    override var id: Long? = null,

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Ordering &&
                id == other.id &&
                status == other.status &&
                createdAt == other.createdAt &&
                updatedAt == other.updatedAt &&
                orderingDetails == other.orderingDetails
    }

    companion object {
        fun of(
            status: OrderingStatus,
            createdAt: LocalDateTime,
            updatedAt: LocalDateTime,
            orderingDetails: List<OrderingDetail>,
            id: Long?
        ) =
            Ordering(status, createdAt, updatedAt, orderingDetails, id)
    }
}
