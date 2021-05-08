package com.wnsgml972.strada.api.v1.order.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.order.service.OrderStatus
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToMany

/**
 * Order
 *
 * 주문 정보의 최상위 Entity
 * table 이름은 order가 keyword라서 orders로 임시 명명 ( 복수형과 단수형에 대해서 의논 필요 )
 *
 * @property status
 * @property createdAt
 * @property orderDetails
 *
 */
@Entity
@Table(name = "ORDERS")
class Order private constructor(
    @Enumerated(EnumType.STRING)
    val status: OrderStatus,

    val createdAt: LocalDateTime,

    @OneToMany
    val orderDetails: List<OrderDetail>,

    override var id: Long? = null,

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Order &&
                id == other.id &&
                status == other.status &&
                createdAt == other.createdAt &&
                orderDetails == other.orderDetails
    }

    companion object {
        fun of(status: OrderStatus, orderDetails: List<OrderDetail>) =
            Order(status, LocalDateTime.now(), orderDetails)
    }
}
