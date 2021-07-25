package com.wnsgml972.strada.api.v1.product.noncoffee.domain

import com.wnsgml972.strada.api.base.AbstractJpaEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class NonCoffee(

    @Id
    @Column(length = 25)
    override var id: String?,

    @Column(length = 128)
    val imageUrl: String,

    @Column
    val price: Int,

    @Column(length = 255)
    val description: String,

    @Column(length = 20)
    val category: String,
) : AbstractJpaEntity<String>() {

    override fun equalProperties(other: Any): Boolean {
        return other is NonCoffee &&
                id == other.id &&
                imageUrl == other.imageUrl &&
                price == other.price &&
                description == other.description &&
                category == other.category
    }

    companion object {
        fun of(
            id: String,
            imageUrl: String,
            price: Int,
            description: String,
            category: String,
        ) = NonCoffee(id, imageUrl, price, description, category)
    }
}
