package com.wnsgml972.strada.api.v1.item.domain

import com.wnsgml972.strada.api.v1.item.service.CoffeeDTO
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable
import java.util.ArrayList
import javax.persistence.*


@Entity
@DynamicInsert
@DynamicUpdate
@Immutable
class BeanCoffee (
    @Id
    @Column(name = "BeanCoffeeId",unique = true, length = 15)
    val id: String = "",

    @Column(name = "coffeeId",unique = true, length = 15)
    val coffeeId: String = "",

    @ManyToOne
    @JoinColumn(name = "beanId")
    val bean: Bean,
    )