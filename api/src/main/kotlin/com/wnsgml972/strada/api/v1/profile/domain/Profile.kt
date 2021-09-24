package com.wnsgml972.strada.api.v1.profile.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import javax.persistence.Column

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Profile private constructor(
    @Id
    override var id: Long?,

    @Column(unique = true)
    val userId: String,

    val point: Long

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is Profile &&
                id == other.id &&
                userId == other.userId &&
                point == other.point
    }

    companion object {
        fun of(
            id: Long?,
            userId: String,
            point: Long
        ) = Profile(id, userId, point)
    }
}
