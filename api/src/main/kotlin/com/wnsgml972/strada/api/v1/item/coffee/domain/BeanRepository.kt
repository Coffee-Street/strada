package com.wnsgml972.strada.api.v1.item.coffee.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface BeanRepository : JpaRepository<Bean, String>{

    @Query("SELECT e FROM Bean e WHERE e.id IN (:id)")
    fun findbyId(id: List<String>): Optional<List<Bean>>
}
