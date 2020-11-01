package com.wnsgml972.strada.user.domain

import com.wnsgml972.strada.user.service.UserRole
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Immutable
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

// TODO: https://thxwelchs.github.io/Kotlin%20JPA%20Entity%20%EB%AA%A8%EB%8D%B8%EB%A7%81%ED%95%98%EA%B8%B0/
@Entity
@Immutable
class User(
    @Id
    @GeneratedValue
    val id: Long,

//    @CreationTimestamp
//    @Column(nullable = false, length = 20, updatable = false)
//    val createdAt: LocalDateTime,
//
//    @UpdateTimestamp
//    @Column(length = 20)
//    val updatedAt: LocalDateTime,

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    val isEnabled: Boolean,

    @Column(nullable = false, unique = true, length = 50)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    val userRole: UserRole
) {
    companion object {
        fun of(
            username: String,
            password: String,
            userRole: UserRole
        ) = User(0, /*LocalDateTime.now(), LocalDateTime.now(),*/
        false, username, password, userRole)
    }
}