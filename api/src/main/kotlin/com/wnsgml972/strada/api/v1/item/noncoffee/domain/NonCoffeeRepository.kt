package com.wnsgml972.strada.api.v1.item.noncoffee.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NonCoffeeRepository : JpaRepository<NonCoffee, String>
