package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.Coffee
import com.wnsgml972.strada.api.v1.item.domain.CoffeeRepository
import com.wnsgml972.strada.api.v1.item.domain.NonCoffeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class NonCoffeeService (
    private val nonCoffeeRepository: NonCoffeeRepository
){

    fun findAll(): List<NonCoffeeDTO> {
        val it = nonCoffeeRepository.findAll()
        val nonCoffees = ArrayList<NonCoffeeDTO>()
        it.forEach { v -> nonCoffees.add(v.toDto()) }

        return nonCoffees
    }

    fun findById(id: String) : NonCoffeeDTO? = nonCoffeeRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun save(nonCoffeeDTO: NonCoffeeDTO)  = nonCoffeeRepository.save(nonCoffeeDTO.toEntity())

}