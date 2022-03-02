package com.wnsgml972.strada.api.v1.purchase.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Purchase private constructor(
    @Column(unique = true)
    val userId: String,

    val orderingId: Long,

    override var id: Long? = null,
): LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Purchase &&
                id == other.id &&
                userId == other.userId &&
                orderingId == other.orderingId
    }

    companion object {
        fun of(
            userId: String,
            orderingId: Long,
            id: Long?
        ) =
            Purchase(userId, orderingId, id)
    }
}