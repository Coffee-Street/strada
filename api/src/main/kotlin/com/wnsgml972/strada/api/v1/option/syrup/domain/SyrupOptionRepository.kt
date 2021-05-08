package com.wnsgml972.strada.api.v1.option.syrup.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SyrupOptionRepository : JpaRepository<SyrupOption, String>
