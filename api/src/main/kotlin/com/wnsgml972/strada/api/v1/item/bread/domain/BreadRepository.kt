package com.wnsgml972.strada.api.v1.item.bread.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BreadRepository : JpaRepository<Bread, String>
