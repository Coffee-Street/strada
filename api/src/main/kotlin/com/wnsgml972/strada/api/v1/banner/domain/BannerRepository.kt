package com.wnsgml972.strada.api.v1.banner.domain

import com.wnsgml972.strada.api.v1.account.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BannerRepository: JpaRepository<Banner, String>{

    fun findByEvalOrder(evalOrder: Int): Optional<Banner>
}