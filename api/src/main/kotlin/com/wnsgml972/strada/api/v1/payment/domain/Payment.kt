package com.wnsgml972.strada.api.v1.payment.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.payment.service.PaymentStatus
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
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

    val paymentStatus: PaymentStatus,

    @JoinColumn(name = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val user: User,

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
                tid == other.tid &&
                paymentStatus == other.paymentStatus &&
                user == other.user
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
            paymentStatus: PaymentStatus,
            user: User,
            id: Long?
        ) = Payment(aid, amount, approvedAt, cid, createdAt, itemName, partnerOrderId,
            partnerUserId, paymentMethodType, quantity, tid, paymentStatus, user, id)
    }
}
