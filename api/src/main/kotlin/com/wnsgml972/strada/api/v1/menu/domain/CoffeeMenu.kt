package com.wnsgml972.strada.api.v1.menu.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@DynamicInsert
@DynamicUpdate
@Immutable
class CoffeeMenu {

    @Id
    @Column(unique = true, length = 15)
    val id: String = ""

    @Column(length = 128)
    val url: String= ""
    @Column
    val price: Int=0

    @Column(length = 255)
    val description: String = ""

    @Column(length = 20)
    val category: String = ""
}
