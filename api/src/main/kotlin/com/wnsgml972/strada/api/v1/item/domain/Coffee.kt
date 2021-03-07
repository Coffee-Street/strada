package com.wnsgml972.strada.api.v1.item.domain

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.data.annotation.PersistenceConstructor
import javax.persistence.*

@Entity
@DynamicInsert
@DynamicUpdate
@Immutable
class Coffee (
    @Id
    @Column(unique = true, length = 15)
    val id: String = "",

    @Column(length = 128)
    val url: String= "",
    @Column
    val price: Int=0,

    @Column(length = 255)
    val description: String = "",

    @Column(length = 20)
    val category: String = "",

    @ManyToOne
    @JoinColumn(name = "coffeeId")
    val beanCoffee: BeanCoffee,


)
