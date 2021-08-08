package com.wnsgml972.strada.api.v1.banner.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import com.wnsgml972.strada.api.v1.item.coffee.domain.BeanCoffee
import com.wnsgml972.strada.api.v1.item.coffee.domain.Coffee
import javax.persistence.Entity


@Entity
@SuppressWarnings("LongParameterList")
class Banner(


): LongJpaEntity(){
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