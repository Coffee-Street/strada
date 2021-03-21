package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.BeanCoffeeRepository
import com.wnsgml972.strada.api.v1.item.domain.Coffee
import com.wnsgml972.strada.api.v1.item.domain.CoffeeRepository
import com.wnsgml972.strada.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoffeeService(
    private val coffeeRepository: CoffeeRepository,
    private val beanCoffeeRepository: BeanCoffeeRepository
) {

    @Transactional()
    fun selectAll(): List<CoffeeDTO> {
        val it = coffeeRepository.findAll()

        val coffees = ArrayList<CoffeeDTO>()

        it.forEach { v -> coffees.add(v.toDto()) }

        return coffees
    }

    fun selectById(id: String): CoffeeDTO? = coffeeRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun insert(coffeeDTO: CoffeeDTO) = coffeeRepository.save(coffeeDTO.toEntity())

    @Transactional
    fun delete(id: String) {


        try{
            coffeeRepository.findByIdOrNull(id)?.beanCoffees?.forEach{
                    v->v.bean = null
                    v.coffee = null
            }
            coffeeRepository.deleteById(id)
        }catch(e: Exception){
            throw NotFoundException()
        }


    }

    @Transactional
    fun insert(coffee: Coffee) = coffeeRepository.save(coffee)
}
