package com.wnsgml972.strada.api.v1.item.coffee.domain

import com.fasterxml.jackson.annotation.JsonBackReference
import com.wnsgml972.strada.api.base.AbstractJpaEntity
import org.hibernate.annotations.Immutable
import javax.persistence.*

@Entity
@Immutable
class Coffee(

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

    //CascadeType.PERSIST 왜썻는지 확인
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "coffee", cascade = [CascadeType.ALL, CascadeType.PERSIST])
    @JsonBackReference(value = "beanCoffeesReference")
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
