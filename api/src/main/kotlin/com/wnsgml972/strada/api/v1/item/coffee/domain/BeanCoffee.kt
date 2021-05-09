package com.wnsgml972.strada.api.v1.item.coffee.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.wnsgml972.strada.api.base.AbstractJpaEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.IdClass
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.CascadeType

@Entity
@IdClass(BeanCoffeeId::class)
class BeanCoffee(

    @GeneratedValue(strategy = GenerationType.AUTO)
    override var id: Int? = 0,

    @Id
    @JoinColumn(name = "coffeeId")
    @JsonManagedReference(value = "beanCoffeeReference")
    @ManyToOne(cascade = [CascadeType.ALL])
    var coffee: Coffee?,

    @Id
    @JoinColumn(name = "beanId")
    @JsonManagedReference(value = "beanCoffeeReference")
    @ManyToOne(cascade = [CascadeType.ALL])
    var bean: Bean?,

) : AbstractJpaEntity<Int>() {

    override fun equalProperties(other: Any): Boolean {
        return other is BeanCoffee &&
                id == other.id &&
                coffee == other.coffee &&
                bean == other.bean
    }

    companion object {
        fun of(id: Int, coffee: Coffee?, bean: Bean?) = BeanCoffee(id, coffee, bean)
    }
}
