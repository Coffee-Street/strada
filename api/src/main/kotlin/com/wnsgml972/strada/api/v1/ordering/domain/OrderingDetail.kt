package com.wnsgml972.strada.api.v1.ordering.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.bean.domain.BeanOption
import com.wnsgml972.strada.api.v1.option.bean.service.BeanOptionDTO
import com.wnsgml972.strada.api.v1.option.bread.domain.BreadOption
import com.wnsgml972.strada.api.v1.option.bread.service.BreadOptionDTO
import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.option.drink.service.DrinkOptionDTO
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne
import javax.persistence.ManyToOne

/**
 * OrderDetail
 *
 * Order 의 하위 객체로 가변적인 주문의 내용을 관리하는 Entity
 *
 * @property ordering
 * @property drinkOption drinkOption
 * @property breadOption breadOption
 * @property beanOption beanOption
 *
 */
@Entity
class OrderingDetail private constructor(
    @ManyToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val ordering: Ordering,

//    val coffee : Coffee?,
//
//    val nonCoffee : NonCoffee?,
//
//    val bread : Bread?,
//
//    val bean : Bean?,

    @OneToOne
    val drinkOption: DrinkOption?,

    @OneToOne
    val breadOption: BreadOption?,

    @OneToOne
    val beanOption: BeanOption?,

    override var id: Long? = null,

    ) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is OrderingDetail &&
                id == other.id &&
                ordering == other.ordering &&
                drinkOption == other.drinkOption &&
                breadOption == other.breadOption &&
                beanOption == other.beanOption
    }

    companion object {
        fun of(ordering: Ordering, drinkOption: DrinkOption?, breadOption: BreadOption?, beanOption: BeanOption?, id: Long?) =
            OrderingDetail(ordering, drinkOption, breadOption, beanOption, id)
    }
}
