package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.Coffee
import com.wnsgml972.strada.api.v1.item.coffee.domain.CoffeeRepository
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
        coffeeRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: String): CoffeeDTO? =
        coffeeRepository.findById(id).orElseThrow { StradaNotFoundException("$id Not Found") }.toDto()

    @Transactional
    fun insert(coffeeDTO: CoffeeDTO): Coffee = coffeeRepository.save(coffeeDTO.toEntity())

    @Transactional
    fun update(coffeeDTO: CoffeeDTO) =
        coffeeRepository.save(coffeeDTO.toEntity())

    @Transactional
    fun delete(id: String) =
        coffeeRepository.findById(id).orElseThrow { StradaNotFoundException("$id Not Found") }
            .run { coffeeRepository.delete(this) }

    companion object : KLogging()
}
