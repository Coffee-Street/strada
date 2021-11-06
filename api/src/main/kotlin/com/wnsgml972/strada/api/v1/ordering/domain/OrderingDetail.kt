package com.wnsgml972.strada.api.v1.ordering.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.option.bean.domain.BeanOption
import com.wnsgml972.strada.api.v1.option.bread.domain.BreadOption
import com.wnsgml972.strada.api.v1.option.drink.domain.DrinkOption
import com.wnsgml972.strada.api.v1.product.bean.domain.Bean
import com.wnsgml972.strada.api.v1.product.bread.domain.Bread
import com.wnsgml972.strada.api.v1.product.coffee.domain.Coffee
import com.wnsgml972.strada.api.v1.product.noncoffee.domain.NonCoffee
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne

/**
 * OrderDetail
 *
 * Order 의 하위 객체로 가변적인 주문의 내용을 관리하는 Entity
 *
 * @property id
 *
 */
@Entity
@SuppressWarnings("LongParameterList")
class OrderingDetail private constructor(
    @OneToOne
    val coffee: Coffee?,

    @OneToOne
    val nonCoffee: NonCoffee?,

    @OneToOne
    val bread: Bread?,

    @OneToOne
    val bean: Bean?,

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
                coffee == other.coffee &&
                nonCoffee == other.nonCoffee &&
                bread == other.bread &&
                bean == other.bean &&
                drinkOption ?: drinkOption == other.drinkOption &&
                breadOption ?: breadOption == other.breadOption &&
                beanOption ?: beanOption == other.beanOption
    }

    companion object {
        fun of(
            coffee: Coffee?,
            nonCoffee: NonCoffee?,
            bread: Bread?,
            bean: Bean?,
            drinkOption: DrinkOption?,
            breadOption: BreadOption?,
            beanOption: BeanOption?,
            id: Long?
        ) =
            OrderingDetail(coffee, nonCoffee, bread, bean, drinkOption, breadOption, beanOption, id)
    }
}
