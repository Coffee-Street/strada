package com.wnsgml972.strada.api.v1.product.coffee.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
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
    override var id: Long?,

    @Column(unique = true)
    val name: String,

    val imageUrl: String,

    val price: Int,

    val description: String,

    val category: String,

    @OneToMany(mappedBy = "coffee", orphanRemoval = true, cascade = [CascadeType.ALL])
    var beanCoffees: List<BeanCoffee> = mutableListOf()

) : LongJpaEntity() {

    override fun equalProperties(other: Any): Boolean {
        return other is Coffee &&
                id == other.id &&
                name == other.name &&
                imageUrl == other.imageUrl &&
                price == other.price &&
                description == other.description &&
                category == other.category &&
                beanCoffees == other.beanCoffees
    }

    companion object {
        fun of(
            id: Long,
            name: String,
            imageUrl: String,
            price: Int,
            description: String,
            category: String,
            beanCoffees: List<BeanCoffee>
        ) = Coffee(id, name, imageUrl, price, description, category, beanCoffees)
    }
}
