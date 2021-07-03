package com.wnsgml972.strada.api.v1.ordering.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.bean.domain.BeanOption
import com.wnsgml972.strada.api.v1.option.bread.domain.BreadOption
import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

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

    @OneToOne(cascade = [CascadeType.ALL])
    val drinkOption: DrinkOption?,

    @OneToOne(cascade = [CascadeType.ALL])
    val breadOption: BreadOption?,

    @OneToOne(cascade = [CascadeType.ALL])
    val beanOption: BeanOption?,

    override var id: Long? = null,

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is OrderingDetail &&
                id == other.id &&
                drinkOption ?: drinkOption == other.drinkOption &&
                breadOption ?: breadOption == other.breadOption &&
                beanOption ?: beanOption == other.beanOption
    }

    companion object {
        fun of(
            ordering: Ordering,
            drinkOption: DrinkOption?,
            breadOption: BreadOption?,
            beanOption: BeanOption?,
            id: Long?
        ) =
            OrderingDetail(ordering, drinkOption, breadOption, beanOption, id)
    }
}
