package com.wnsgml972.strada.api.v1.product.coffee.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BeanCoffeeRepository : JpaRepository<BeanCoffee, BeanCoffeeId>{

    fun findByBeanId(id: String): Optional<List<BeanCoffee>>
}
