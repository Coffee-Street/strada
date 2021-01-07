package com.wnsgml972.strada.api.v1.account.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.Immutable
import javax.persistence.Column
import javax.persistence.Entity

@Entity
@DynamicInsert
@DynamicUpdate
@Immutable
class User : LongJpaEntity() {

    @Column(unique = true, length = 15)
    val username: String = ""

    @Column
    var isEnabled: Boolean = true

    override fun equalProperties(other: Any): Boolean {
        TODO("Not yet implemented")
    }
}
