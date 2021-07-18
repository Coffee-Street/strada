package com.wnsgml972.strada.api.v1.item.coffee.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.wnsgml972.strada.api.base.AbstractJpaEntity
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.OneToMany
import javax.persistence.CascadeType

@Entity
@SuppressWarnings("LongParameterList")
class Coffee(

    @Id
    @Column(length = 25)
    override var id: String?,

    val imageUrl: String,

    val price: Int,

    val description: String,

    val category: String,

    @OneToMany(mappedBy = "coffee", orphanRemoval = true, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var beanCoffees: List<BeanCoffee> = mutableListOf()

) : AbstractJpaEntity<String>() {

    override fun equalProperties(other: Any): Boolean {
        return other is Coffee &&
                id == other.id &&
                imageUrl == other.imageUrl &&
                price == other.price &&
                description == other.description &&
                category == other.category &&
                beanCoffees == other.beanCoffees
    }

    companion object {
        fun of(
            id: String,
            imageUrl: String,
            price: Int,
            description: String,
            category: String,
            beanCoffees: List<BeanCoffee>
        ) = Coffee(id, imageUrl, price, description, category, beanCoffees)
    }
}
