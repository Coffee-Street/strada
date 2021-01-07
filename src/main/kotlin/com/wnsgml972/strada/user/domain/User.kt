package com.wnsgml972.strada.user.domain

import org.hibernate.annotations.Immutable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

// TODO: https://thxwelchs.github.io/Kotlin%20JPA%20Entity%20%EB%AA%A8%EB%8D%B8%EB%A7%81%ED%95%98%EA%B8%B0/
@Entity
@Immutable
class User(
    @Id
    @GeneratedValue
    val id: Long,
    val username: String,
    val password: String,
    val isEnabled: Boolean,
    val role: String
)
