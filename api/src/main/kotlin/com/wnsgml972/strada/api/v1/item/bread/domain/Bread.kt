package com.wnsgml972.strada.api.v1.item.bread.domain

import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
@Immutable
class Bread(
    @Id
    @Column( length = 25)
    @NotEmpty
    val id: String = "",

    @Column(length = 128)
    val url: String = "",

    @Column
    val price: Int = 0,

    @Column(length = 255)
    val description: String = "",

    @Column(length = 20)
    val category: String = "",

)
