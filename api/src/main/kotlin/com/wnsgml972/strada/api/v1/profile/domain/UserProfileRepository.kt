package com.wnsgml972.strada.api.v1.profile.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserProfileRepository : JpaRepository<UserProfile, Long> {
    fun findByUserId(userId: String): Optional<UserProfile>
}
