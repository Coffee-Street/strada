package com.wnsgml972.strada.api.v1.account.domain

import com.wnsgml972.strada.api.base.AbstractJpaEntity
import javax.persistence.*

@Entity
class User(
    @Id
    override var id: String?,

    @Column
    val isEnabled: Boolean,
) : AbstractJpaEntity<String>() {

    override fun equalProperties(other: Any): Boolean {
        return other is User &&
                id == other.id &&
                isEnabled == other.isEnabled
    }
}
