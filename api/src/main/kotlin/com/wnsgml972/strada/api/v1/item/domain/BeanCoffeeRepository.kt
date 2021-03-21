package com.wnsgml972.strada.api.v1.item.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BeanCoffeeRepository : JpaRepository<BeanCoffee, String> {
    // override fun findById(id: String): Optional<Coffee>
    fun findByCoffee(id: String) : Optional<BeanCoffee>
}
