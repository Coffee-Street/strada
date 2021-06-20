package com.wnsgml972.strada.api.v1.ordering.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne

/**
 * OrderDetail
 *
 * Order 의 하위 객체로 가변적인 주문의 내용을 관리하는 Entity
 *
 * @property id
 * @property ordering
 *
 */
@Entity
class OrderingDetail private constructor(
    @ManyToOne(cascade = [CascadeType.ALL])
    val ordering: Ordering,

//    val coffee : Coffee?,
//
//    val nonCoffee : NonCoffee?,
//
//    val bread : Bread?,
//
//    val bean : Bean?,
//
//    @OneToOne
//    val drinkOption: DrinkOption?,
//
//    @OneToOne
//    val breadOption: BreadOption?,
//
//    @OneToOne
//    val beanOption: BeanOption?,

    override var id: Long? = null,

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is OrderingDetail &&
                id == other.id &&
                ordering == other.ordering
    }

    companion object {
        fun of(ordering: Ordering, id: Long?) =
            OrderingDetail(ordering, id)
    }
}
