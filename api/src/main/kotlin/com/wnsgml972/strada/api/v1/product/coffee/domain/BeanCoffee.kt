package com.wnsgml972.strada.api.v1.product.coffee.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.ManyToOne
import javax.persistence.Entity
import javax.persistence.IdClass
import javax.persistence.JoinColumn
import javax.persistence.FetchType
import javax.persistence.CascadeType

@Entity
@IdClass(BeanCoffeeId::class)
class BeanCoffee(


    @JoinColumn(name = "coffeeId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var coffee: Coffee?,


    @JoinColumn(name = "beanId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var bean: Bean?,

) : LongJpaEntity() {

    override fun equalProperties(other: Any): Boolean {
        return other is BeanCoffee &&
                coffee == other.coffee &&
                bean == other.bean
    }

    companion object {
        fun of(coffee: Coffee?, bean: Bean?) = BeanCoffee(coffee, bean)
    }
}

//
//@Entity
//@IdClass(BeanCoffeeId::class)
//class BeanCoffee(
//
//    @Id
//    @JoinColumn(name = "coffeeId")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
//    var coffee: Coffee?,
//
//    @Id
//    @JoinColumn(name = "beanId")
//    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
//    var bean: Bean?,
//
//    ) : LongJpaEntity() {
//
//    override fun equalProperties(other: Any): Boolean {
//        return other is BeanCoffee &&
//                coffee == other.coffee &&
//                bean == other.bean
//    }
//
//    companion object {
//        fun of(coffee: Coffee?, bean: Bean?) = BeanCoffee(coffee, bean)
//    }
//}
