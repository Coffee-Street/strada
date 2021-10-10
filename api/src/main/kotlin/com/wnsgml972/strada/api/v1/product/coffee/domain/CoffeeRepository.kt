package com.wnsgml972.strada.api.v1.product.coffee.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CoffeeRepository : JpaRepository<Coffee, String> {

    @Query(value = "select DISTINCT c from Coffee c  " +
            "inner join fetch c.beanCoffees d " +
            "inner join fetch d.bean where c.name=:name")
    fun findByName(name: String): Optional<Coffee>
}
