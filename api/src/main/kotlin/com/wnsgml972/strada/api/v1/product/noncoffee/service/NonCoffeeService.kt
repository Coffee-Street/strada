package com.wnsgml972.strada.api.v1.product.noncoffee.service

import com.wnsgml972.strada.api.v1.product.noncoffee.domain.NonCoffee
import com.wnsgml972.strada.api.v1.product.noncoffee.domain.NonCoffeeRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import mu.KLogging
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
        load(id)
            .toDto()

    @Transactional
    fun insert(nonCoffeeDTO: NonCoffeeDTO): NonCoffeeDTO =
        nonCoffeeRepository
            .save(nonCoffeeDTO.toEntity())
            .toDto()

    @Transactional
    fun update(nonCoffeeDTO: NonCoffeeDTO): NonCoffeeDTO =
        load(nonCoffeeDTO.id)
            .let {
                nonCoffeeRepository.save(nonCoffeeDTO.toEntity())
            }
            .toDto()

    @Transactional
    fun delete(id: String) =
        load(id)
            .let {
                nonCoffeeRepository.delete(it)
            }

    @Transactional(readOnly = true)
    fun load(id: String): NonCoffee =
        nonCoffeeRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }

    companion object : KLogging()
}
