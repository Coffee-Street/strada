package com.wnsgml972.strada.api.v1.item.coffee.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BeanCoffeeRepository : JpaRepository<BeanCoffee, String>
