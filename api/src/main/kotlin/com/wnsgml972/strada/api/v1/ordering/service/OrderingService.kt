package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.item.bread.service.BreadService
import com.wnsgml972.strada.api.v1.ordering.domain.OrderingRepository
import com.wnsgml972.strada.api.v1.product.bean.service.BeanService
import com.wnsgml972.strada.api.v1.product.bean.service.toEntity
import com.wnsgml972.strada.api.v1.product.bread.service.toEntity
import com.wnsgml972.strada.api.v1.product.coffee.service.CoffeeService
import com.wnsgml972.strada.api.v1.product.coffee.service.toEntity
import com.wnsgml972.strada.api.v1.product.noncoffee.service.NonCoffeeService
import com.wnsgml972.strada.api.v1.product.noncoffee.service.toEntity
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderingService(
    private val orderingRepository: OrderingRepository,
    private val coffeeService: CoffeeService,
    private val nonCoffeeService: NonCoffeeService,
    private val breadService: BreadService,
    private val beanService: BeanService
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<OrderingDTO> {
        return orderingRepository
            .findAll()
            .map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun selectById(id: Long): OrderingDTO {
        return orderingRepository
            .findByIdOrNull(id)?.toDto() ?: throw StradaNotFoundException("$id is not found")
    }

    @Transactional
    fun insert(orderingRequest: OrderingRequest): OrderingDTO {
        val tt = orderingRequest.orderingDetailRequests.map {
            it.toEntity(
                it.coffeeName?.let { coffeeService.select(it).toEntity() },
                it.nonCoffeeName?.let { nonCoffeeService.selectById(it).toEntity() },
                it.breadName?.let { breadService.selectById(it).toEntity() },
                it.beanName?.let { beanService.selectById(it).toEntity() }
            )
        }
        return orderingRepository
            .save(orderingRequest.toEntity(tt))
            .toDto()
    }

    @Transactional
    fun update(id: Long, orderingRequest: OrderingRequest): OrderingDTO {
        return orderingRepository.findById(id)
            .orElseThrow { StradaNotFoundException("$id is not found") }
            .let { orderingRepository.save(orderingRequest.toEntity(id)).toDto() }
    }

    @Transactional
    fun delete(id: Long) {
        orderingRepository.findById(id)
            .orElseThrow { StradaNotFoundException("$id is not found") }
            .run { orderingRepository.delete(this) }
    }
}
