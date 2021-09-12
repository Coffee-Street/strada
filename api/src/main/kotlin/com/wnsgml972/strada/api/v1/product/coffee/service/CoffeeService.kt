package com.wnsgml972.strada.api.v1.product.coffee.service

import com.wnsgml972.strada.api.v1.product.coffee.domain.CoffeeRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoffeeService(
    private val coffeeRepository: CoffeeRepository,
) {

    @Transactional(readOnly = true)
    fun selectAll(): List<CoffeeDTO> =
        coffeeRepository
            .findAll()
            .map { it.toDto() }

    @Transactional(readOnly = true)
    fun select(name: String): CoffeeDTO =
        coffeeRepository
            .findByName(name)
            .orElseThrow { StradaNotFoundException("$name Not Found") }
            .toDto()

    @Transactional
    fun insert(coffeeDTO: CoffeeDTO): CoffeeDTO =
        coffeeRepository
            .save(coffeeDTO.toEntity())
            .toDto()

    @Transactional
    fun update(coffeeDTO: CoffeeDTO): CoffeeDTO =
        coffeeRepository
            .findByName(coffeeDTO.name)
            .orElseThrow { StradaNotFoundException("${coffeeDTO.name} Not Found") }
            .let {
                it.id ?: throw StradaNotFoundException("${it.id} is not initialized")
                coffeeRepository
                        .save(coffeeDTO.toEntity(it.id!!))
            }
            .toDto()

    @Transactional
    fun delete(name: String) =
        coffeeRepository
            .findByName(name)
            .orElseThrow { StradaNotFoundException("$name Not Found") }
            .let {
                coffeeRepository.delete(it)
            }

    companion object : KLogging()
}
