package com.wnsgml972.strada.api.v1.item.coffee.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CoffeeRepository : JpaRepository<Coffee, String>{

    @Query(value = "select DISTINCT c from Coffee c  inner join fetch c.beanCoffees d inner join fetch d.bean")
    fun findbyName(name: String): Optional<Coffee>
}
