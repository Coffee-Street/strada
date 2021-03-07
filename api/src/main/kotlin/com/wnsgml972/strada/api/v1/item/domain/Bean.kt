package com.wnsgml972.strada.api.v1.item.domain

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable
import javax.persistence.*

@Entity
@DynamicInsert
@DynamicUpdate
@Immutable
class Bean (
    @Id
    @Column(name = "beanId",unique = true, length = 15)
    val id: String = "",

    @Column(length = 128)
    val origin: String= "",
    @Column(length = 128)
    val farm: String= "",
    @Column(length = 128)
    val description: String= "",
    @Column(length = 128)
    val processing: String= "",
    @Column(length = 128)
    val roasting: String= "",
    @Column(length = 128)
    val kind: String= "",
    @Column(length = 128)
    val grade: String= "",


)