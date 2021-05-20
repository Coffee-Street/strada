package com.wnsgml972.strada.api.v1.account.domain

import com.wnsgml972.strada.api.base.AbstractJpaEntity
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Column

@Entity
@DynamicUpdate
class User private constructor(
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

    companion object {
        fun of(id: String, isEnabled: Boolean) = User(id, isEnabled)
    }
}
