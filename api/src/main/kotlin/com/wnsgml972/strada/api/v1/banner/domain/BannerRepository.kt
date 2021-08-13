package com.wnsgml972.strada.api.v1.banner.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BannerRepository : JpaRepository<Banner, String> {

    fun findByCode(code: String): Optional<Banner>
}
