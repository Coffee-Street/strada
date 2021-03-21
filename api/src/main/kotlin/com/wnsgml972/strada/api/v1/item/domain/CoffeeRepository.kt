package com.wnsgml972.strada.api.v1.item.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CoffeeRepository : JpaRepository<Coffee, String> {
    // override fun findById(id: String): Optional<Coffee>

}
