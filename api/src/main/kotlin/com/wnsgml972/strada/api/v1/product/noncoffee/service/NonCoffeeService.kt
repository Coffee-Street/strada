package com.wnsgml972.strada.api.v1.product.noncoffee.service

import com.wnsgml972.strada.api.v1.product.noncoffee.domain.NonCoffeeRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NonCoffeeService(
    private val nonCoffeeRepository: NonCoffeeRepository
) {

    @Transactional(readOnly = true)
    fun selectAll(): List<NonCoffeeDTO> =
        nonCoffeeRepository
            .findAll()
            .map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: String): NonCoffeeDTO =
        nonCoffeeRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id is not found") }
            .toDto()

    @Transactional
    fun insert(nonCoffeeDTO: NonCoffeeDTO): NonCoffeeDTO =
        nonCoffeeRepository
            .save(nonCoffeeDTO.toEntity())
            .toDto()

    @Transactional
    fun update(nonCoffeeDTO: NonCoffeeDTO): NonCoffeeDTO =
        nonCoffeeRepository
            .findById(nonCoffeeDTO.id)
            .orElseThrow { StradaNotFoundException("${nonCoffeeDTO.id} is not found") }
            .let {
                nonCoffeeRepository.save(nonCoffeeDTO.toEntity())
            }
            .toDto()

    @Transactional
    fun delete(id: String) = nonCoffeeRepository
        .findById(id)
        .orElseThrow { StradaNotFoundException("$id is not found") }
        .let {
            nonCoffeeRepository.delete(it)
        }
}
