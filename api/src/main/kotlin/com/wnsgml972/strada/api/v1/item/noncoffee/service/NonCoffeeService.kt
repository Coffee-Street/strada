package com.wnsgml972.strada.api.v1.item.noncoffee.service

import com.wnsgml972.strada.api.v1.item.coffee.service.toEntity
import com.wnsgml972.strada.api.v1.item.noncoffee.domain.NonCoffeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NonCoffeeService(
    private val nonCoffeeRepository: NonCoffeeRepository
) {

    fun selectAll(): List<NonCoffeeDTO> {
        val nonCoffees = ArrayList<NonCoffeeDTO>()
        nonCoffeeRepository.findAll().forEach { v -> nonCoffees.add(v.toDto()) }
        return nonCoffees
    }

    fun selectById(id: String): NonCoffeeDTO? = nonCoffeeRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun insert(nonCoffeeDTO: NonCoffeeDTO) = nonCoffeeRepository.save(nonCoffeeDTO.toEntity())

    @Transactional
    fun update(nonCoffeeDTO: NonCoffeeDTO) = nonCoffeeRepository.save(nonCoffeeDTO.toEntity())

    @Transactional
    fun delete(id: String) = nonCoffeeRepository.deleteById(id)
}
