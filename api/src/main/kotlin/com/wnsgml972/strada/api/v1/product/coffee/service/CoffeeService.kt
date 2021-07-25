package com.wnsgml972.strada.api.v1.product.coffee.service

import com.wnsgml972.strada.api.v1.product.coffee.domain.Coffee
import com.wnsgml972.strada.api.v1.product.coffee.domain.CoffeeRepository
import com.wnsgml972.strada.exception.NotFoundException
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
    fun select(name: String): CoffeeDTO? =
        coffeeRepository.findByName(name).orElseThrow { NotFoundException("$name Not Found") }.toDto()

    @Transactional
    fun insert(coffeeDTO: CoffeeDTO): Coffee = coffeeRepository.save(coffeeDTO.toEntity())

//    @Transactional
//    fun update(coffeeDTO: CoffeeDTO) =
//        coffeeRepository.findByName(coffeeDTO.name).orElseThrow { NotFoundException("${coffeeDTO.name} Not Found") }
//            .id?.let {
//                result = coffeeRepository.save(coffeeDTO.toEntity(it))
//            }

    @Transactional
    fun update(coffeeDTO: CoffeeDTO) =
        coffeeRepository.findByName(coffeeDTO.name).orElseThrow { NotFoundException("${coffeeDTO.name} Not Found") }
            .id?.let {
                coffeeRepository.save(coffeeDTO.toEntity(it))
            }

    @Transactional
    fun delete(name: String) =
        coffeeRepository.findByName(name).orElseThrow { NotFoundException("$name Not Found") }
            .run { coffeeRepository.delete(this) }

    companion object : KLogging()
}
