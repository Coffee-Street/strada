package com.wnsgml972.strada.api.v1.option.bread.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BreadOptionRepository : JpaRepository<BreadOption, String>
