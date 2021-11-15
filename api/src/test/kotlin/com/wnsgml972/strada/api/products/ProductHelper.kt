package com.wnsgml972.strada.api.products

import com.wnsgml972.strada.api.v1.item.bread.service.BreadService
import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
import com.wnsgml972.strada.api.v1.product.bean.service.BeanService
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeService
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeDTO
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductHelper @Autowired constructor(
    private val coffeeService: CoffeeService,
    private val beanService: BeanService,
    private val breadService: BreadService,
    private val nonCoffeeService: NonCoffeeService
){

    @Transactional
    fun insertCoffee(coffeeDTO: CoffeeDTO) =
        coffeeService.insert(coffeeDTO)

    @Transactional
    fun deleteCoffee(coffeeName: String) =
        coffeeService.delete(coffeeName)

    @Transactional
    fun insertBean(beanDTO: BeanDTO) =
        beanService.insert(beanDTO)

    @Transactional
    fun deleteBean(beanId: String) =
        beanService.delete(beanId)

    @Transactional
    fun clearCoffee() =
        coffeeService
            .selectAll()
            .forEach {
                coffeeService
                    .delete(it.name)
            }

    @Transactional
    fun clearBean() =
        beanService
            .selectAll()
            .forEach{
                it.id?.let { id ->
                    beanService
                        .delete(id)
                }
            }

    @Transactional
    fun insertBread(breadDTO: BreadDTO) =
        breadService.insert(breadDTO)

    @Transactional
    fun deleteBread(breadId: String) =
        breadService.delete(breadId)

    @Transactional
    fun clearBread() =
        breadService
            .selectAll()
            .forEach {
                breadService.delete(it.id)
            }

    @Transactional
    fun insertNonCoffee(nonCoffeeDTO: NonCoffeeDTO) =
        nonCoffeeService.insert(nonCoffeeDTO)

    @Transactional
    fun deleteNonCoffee(nonCoffeeId: String) =
        nonCoffeeService.delete(nonCoffeeId)

    @Transactional
    fun clearNonCoffee() =
        nonCoffeeService
            .selectAll()
            .forEach {
                nonCoffeeService.delete(it.id)
            }

    fun test() =
        coffeeService.load("aaa")

}

