package com.wnsgml972.strada.api.v1.item.domain

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.CascadeType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
@DynamicInsert
@DynamicUpdate
@Immutable
class BeanCoffee(
    @Id
    @Column(name = "BeanCoffeeId", unique = true, length = 15)
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String = "",

    @JoinColumn(name = "coffeeId")
    @JsonManagedReference(value = "beanCoffeeReference")
    @ManyToOne(cascade = [CascadeType.ALL])
    //@ManyToOne
    var coffee: Coffee?,

    @JoinColumn(name = "beanId")
    @JsonManagedReference(value = "beanCoffeeReference")
    @ManyToOne(cascade = [CascadeType.ALL])
    //@ManyToOne
    var bean: Bean?,


)

/*
* {
*   id: americano
*   url:~~~~~
*   [
*       beanCoffee1 {},
*       beanCoffee2 {},
*   ]
* }
*
* */
