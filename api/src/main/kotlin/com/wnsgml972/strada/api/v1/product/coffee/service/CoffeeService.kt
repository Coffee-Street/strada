package com.wnsgml972.strada.api.v1.product.coffee.service

import com.wnsgml972.strada.api.v1.product.coffee.domain.Coffee
import com.wnsgml972.strada.api.v1.product.coffee.domain.CoffeeRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
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
        load(name)
            .toDto()

    @Transactional
    fun insert(coffeeDTO: CoffeeDTO): CoffeeDTO =
        coffeeRepository
            .save(coffeeDTO.toEntity())
            .toDto()

    @Transactional
    fun update(coffeeDTO: CoffeeDTO): CoffeeDTO =
        load(coffeeDTO.name)
            .let {
                coffeeRepository.save(coffeeDTO.toEntity(it.id!!))
            }
            .toDto()

    @Transactional
    fun delete(name: String) =
        load(name)
            .let {
                coffeeRepository.delete(it)
            }

    @Transactional(readOnly = true)
    fun load(name: String): Coffee =
        coffeeRepository
            .findByName(name)
            .orElseThrow { StradaNotFoundException("$name Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }

    companion object : KLogging()
}
