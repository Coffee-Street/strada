package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.Coffee
import com.wnsgml972.strada.api.v1.item.domain.CoffeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class CoffeeService (
    private val coffeeRepository: CoffeeRepository
){

    fun findAll(): List<CoffeeDTO> {
        val it = coffeeRepository.findAll()
        val coffees = ArrayList<CoffeeDTO>()
        it.forEach { v -> coffees.add(v.toDto()) }

        return coffees
    }

    fun findById(id: String) : CoffeeDTO? = coffeeRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun save(coffeeDTO: CoffeeDTO)  = coffeeRepository.save(coffeeDTO.toEntity())

}