package com.wnsgml972.strada.api.products

import com.wnsgml972.strada.api.v1.product.bean.service.BeanDTO
import com.wnsgml972.strada.api.v1.product.bean.service.BeanService
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.bread.service.BreadService
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeDTO
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeService
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeDTO
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductHelper @Autowired constructor(
    private val coffeeService: CoffeeService,
    private val beanService: BeanService,
    private val breadService: BreadService,
    private val nonCoffeeService: NonCoffeeService
){
    fun insertCoffee(coffeeDTO: CoffeeDTO) =
        coffeeService.insert(coffeeDTO)

    fun deleteCoffee(coffeeName: String) =
        coffeeService.delete(coffeeName)

    fun insertBean(beanDTO: BeanDTO) =
        beanService.insert(beanDTO)

    fun deleteBean(beanId: String) =
        beanService.delete(beanId)

    fun clearCoffee() =
        coffeeService
            .selectAll()
            .forEach {
                coffeeService
                    .delete(it.name)
            }

    fun clearBean() =
        beanService
            .selectAll()
            .forEach{
                it.id?.let { id ->
                    beanService
                        .delete(id)
                }
            }
    fun insertBread(breadDTO: BreadDTO) =
        breadService.insert(breadDTO)

    fun deleteBread(breadId: String) =
        breadService.delete(breadId)

    fun clearBread() =
        breadService
            .selectAll()
            .forEach {
                breadService.delete(it.id)
            }

    fun insertNonCoffee(nonCoffeeDTO: NonCoffeeDTO) =
        nonCoffeeService.insert(nonCoffeeDTO)

    fun deleteNonCoffee(nonCoffeeId: String) =
        nonCoffeeService.delete(nonCoffeeId)

    fun clearNonCoffee() =
        nonCoffeeService
            .selectAll()
            .forEach {
                nonCoffeeService.delete(it.id)
            }

}

