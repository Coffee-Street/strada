package com.wnsgml972.strada.api.v1.payment.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
@SuppressWarnings("LongParameterList")
class Payment private constructor(
    val aid: String,

    @OneToOne(cascade = [CascadeType.ALL])
    val amount: Amount,

    val approvedAt: String,

    val cid: String,

    val createdAt: String,

    val itemName: String,

    val partnerOrderId: String,

    val partnerUserId: String,

    val paymentMethodType: String,

    val quantity: Int,

    val tid: String,

    override var id: Long? = null,

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Payment &&
                id == other.id &&
                aid == other.aid &&
                amount == other.amount &&
                approvedAt == other.approvedAt &&
                cid == other.cid &&
                createdAt == other.createdAt &&
                itemName == other.itemName &&
                partnerOrderId == other.partnerOrderId &&
                partnerUserId == other.partnerUserId &&
                paymentMethodType == other.paymentMethodType &&
                quantity == other.quantity &&
                tid == other.tid
    }

    companion object {
        fun of(
            aid: String,
            amount: Amount,
            approvedAt: String,
            cid: String,
            createdAt: String,
            itemName: String,
            partnerOrderId: String,
            partnerUserId: String,
            paymentMethodType: String,
            quantity: Int,
            tid: String,
            id: Long?
        ) = Payment(aid, amount, approvedAt, cid, createdAt, itemName, partnerOrderId,
            partnerUserId, paymentMethodType, quantity, tid, id)
    }
}
