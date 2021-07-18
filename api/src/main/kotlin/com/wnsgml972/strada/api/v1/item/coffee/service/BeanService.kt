package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.BeanCoffeeRepository
import com.wnsgml972.strada.api.v1.item.coffee.domain.BeanRepository
import com.wnsgml972.strada.exception.NotFoundException
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BeanService(
    private val beanRepository: BeanRepository,
    private val beanCoffeeService: BeanCoffeeRepository
)  {

    @Transactional
    fun delete(id: String) =
        beanCoffeeService.findByBeanId(id).orElseThrow { NotFoundException("$id Not Found") }
            .map { beanCoffeeService.delete(it) }.run{
                beanRepository.findById(id).orElseThrow { NotFoundException("$id Not Found") }
                    .run { beanRepository.delete(this) }
            }



    companion object : KLogging()
}