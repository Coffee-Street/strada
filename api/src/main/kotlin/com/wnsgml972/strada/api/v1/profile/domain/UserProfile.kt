package com.wnsgml972.strada.api.v1.profile.domain

import com.wnsgml972.strada.api.base.LongJpaEntity
import org.hibernate.validator.constraints.Length
import javax.persistence.Column

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Positive

/**
 * UserProfile
 *
 * User와 관련된 속성 중 User 도메인에서 자주 변경될 속성을 Profile로 분리시킨 Entity
 *
 * @property userId User Entity의 id를 가지고 있음 ( User Entity id는 전화번호 )
 * @property point User에게 제공된 혜택으로 결제에 사용할 수 있음
 *
 */
@Entity
class UserProfile private constructor(
    @Id
    override var id: Long?,

    @Column(unique = true)
    @Length(min = 1)
    val userId: String,

    @Positive
    val point: Long

) : LongJpaEntity() {
    override fun equalProperties(other: Any): Boolean {
        return other is UserProfile &&
                id == other.id &&
                userId == other.userId &&
                point == other.point
    }

    companion object {
        fun of(
            id: Long?,
            userId: String,
            point: Long
        ) = UserProfile(id, userId, point)
    }
}
