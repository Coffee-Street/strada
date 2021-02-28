package com.wnsgml972.strada.api.v1.menu.service

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity



data class MenuDTO(

    val id: String,
    val url: String,
    val price: Int,
    val description: String,
    val category: String,
)
