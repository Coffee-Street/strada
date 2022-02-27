package com.wnsgml972.strada.api.v1.payment.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.Entity

@Entity
@SuppressWarnings("LongParameterList")
class Amount private constructor(
    val discount: Int,
    val point: Int,
    val taxFree: Int,
    val total: Int,
    val vat: Int,
    override var id: Long? = null,

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Amount &&
                id == other.id &&
                discount == other.discount &&
                point == other.point &&
                taxFree == other.taxFree &&
                total == other.total &&
                vat == other.vat
    }

    companion object {
        fun of(discount: Int, point: Int, taxFree: Int, total: Int, vat: Int, id: Long?) =
            Amount(discount, point, taxFree, total, vat, id)
    }
}
