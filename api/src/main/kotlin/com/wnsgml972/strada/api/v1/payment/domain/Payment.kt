package com.wnsgml972.strada.api.v1.payment.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.account.domain.User
import com.wnsgml972.strada.api.v1.payment.service.PaymentStatus
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
@EntityListeners(AuditingEntityListener::class)
@SuppressWarnings("LongParameterList", "complexity")
class Payment private constructor(
    val aid: String?,

    @OneToOne(cascade = [CascadeType.ALL])
    val amount: Amount?,

    val approvedAt: LocalDateTime?,

    val cid: String,

    @CreatedDate
    var createdAt: LocalDateTime?,

    val itemName: String,

    val partnerOrderId: String,

    val partnerUserId: String,

    val paymentMethodType: String?,

    val quantity: Int,

    val tid: String?,

    val paymentStatus: PaymentStatus,

    @JoinColumn
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
            aid: String?,
            amount: Amount?,
            approvedAt: LocalDateTime?,
            cid: String,
            itemName: String,
            partnerOrderId: String,
            partnerUserId: String,
            paymentMethodType: String?,
            quantity: Int,
            tid: String?,
            paymentStatus: PaymentStatus,
            user: User,
            id: Long?
        ) = Payment(aid ?: null,
            amount ?: null,
            approvedAt ?: null,
            cid,
            null,
            itemName,
            partnerOrderId,
            partnerUserId,
            paymentMethodType ?: null,
            quantity,
            tid ?: null,
            paymentStatus,
            user,
            id)
    }
}
