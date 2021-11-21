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
    fun insert(name: String, coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        coffeeRepository
            .save(coffeeInsertRequest.toEntity(name))
            .toDto()

    @Transactional
    fun update(name: String, coffeeInsertRequest: CoffeeInsertRequest): CoffeeDTO =
        load(name)
            .let {
                coffeeRepository.save(coffeeInsertRequest.toEntity(it.id!!, name))
            }
            .toDto()

    @Transactional
    fun delete(name: String) =
        load(name)
            .let {
                coffeeRepository.delete(it)
            }

    // @Transactional(readOnly = true)
    private fun load(name: String): Coffee =
        coffeeRepository
            .findByName(name)
            .orElseThrow { StradaNotFoundException("$name Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }

    companion object : KLogging()
}
