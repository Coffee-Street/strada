package com.wnsgml972.strada.api.v1.item.coffee.domain

import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotEmpty

@Entity
@Immutable
@SuppressWarnings("LongParameterList")
class Bean(
    @Id
    @Column(length = 25)
    @NotEmpty
    val id: String = "",

    @Column(length = 128)
    val origin: String = "",

    @Column(length = 128)
    val farm: String = "",

    @Column(length = 128)
    val description: String = "",

    @Column(length = 128)
    val processing: String = "",

    @Column(length = 128)
    val roasting: String = "",

    @Column(length = 128)
    val kind: String = "",

    @Column(length = 128)
    val grade: String = "",

)
