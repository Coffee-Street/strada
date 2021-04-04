package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.CoffeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoffeeService(
    private val coffeeRepository: CoffeeRepository,
) {

    @Transactional()
    fun selectAll(): List<CoffeeDTO> {
        val coffees = ArrayList<CoffeeDTO>()
        coffeeRepository.findAll().forEach { v -> coffees.add(v.toDto()) }
        return coffees
    }

    fun selectById(id: String): CoffeeDTO? = coffeeRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun insert(coffeeDTO: CoffeeDTO) = coffeeRepository.save(coffeeDTO.toEntity())

    @Transactional
    fun update(coffeeDTO: CoffeeDTO) = coffeeRepository.save(coffeeDTO.toEntity())

    @Transactional
    fun delete(id: String) {

        coffeeRepository.findByIdOrNull(id)?.beanCoffees?.forEach { v ->
            v.bean = null
            v.coffee = null
        }
        coffeeRepository.deleteById(id)
    }
}
