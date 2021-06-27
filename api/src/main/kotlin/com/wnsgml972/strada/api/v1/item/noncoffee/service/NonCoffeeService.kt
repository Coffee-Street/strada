package com.wnsgml972.strada.api.v1.item.noncoffee.service

import com.wnsgml972.strada.api.v1.item.noncoffee.domain.NonCoffeeRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NonCoffeeService(
    private val nonCoffeeRepository: NonCoffeeRepository
) {

    @Transactional(readOnly = true)
    fun selectAll(): List<NonCoffeeDTO> = nonCoffeeRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: String): NonCoffeeDTO? = nonCoffeeRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun insert(nonCoffeeDTO: NonCoffeeDTO) = nonCoffeeRepository.save(nonCoffeeDTO.toEntity())

    @Transactional
    fun update(nonCoffeeDTO: NonCoffeeDTO) = nonCoffeeRepository.save(nonCoffeeDTO.toEntity())

    @Transactional
    fun delete(id: String) = nonCoffeeRepository
        .findById(id)
        .orElseThrow { StradaNotFoundException("$id is not found") }
        .run { nonCoffeeRepository.delete(this) }
}
