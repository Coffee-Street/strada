package com.wnsgml972.strada.api.v1.option.drizzle.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DrizzleOptionRepository : JpaRepository<DrizzleOption, String>
