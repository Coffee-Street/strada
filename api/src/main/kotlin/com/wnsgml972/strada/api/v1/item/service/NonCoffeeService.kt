package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.NonCoffeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NonCoffeeService(
    private val nonCoffeeRepository: NonCoffeeRepository
) {

    fun selectAll(): List<NonCoffeeDTO> {
        val it = nonCoffeeRepository.findAll()

        val nonCoffees = ArrayList<NonCoffeeDTO>()
        it.forEach { v -> nonCoffees.add(v.toDto()) }

        return nonCoffees
    }

    fun selectById(id: String): NonCoffeeDTO? = nonCoffeeRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun insert(nonCoffeeDTO: NonCoffeeDTO) = nonCoffeeRepository.save(nonCoffeeDTO.toEntity())
}
