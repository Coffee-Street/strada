package com.wnsgml972.strada.api.v1.account.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    fun findByUsername(username: String): Optional<User>
}
