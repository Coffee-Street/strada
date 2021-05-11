package com.wnsgml972.strada.api.v1.order.service

import com.wnsgml972.strada.api.v1.order.domain.OrderRepository
import com.wnsgml972.strada.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<OrderDTO> = orderRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: Long): OrderDTO? = orderRepository.findByIdOrNull(id)?.toDto() ?: throw NotFoundException("id: $id NotFound")

    @Transactional
    fun insert(orderDTO: OrderDTO) = orderRepository.save(orderDTO.toEntity())

    @Transactional
    fun update(orderDTO: OrderDTO) = orderRepository.save(orderDTO.toEntity())

    @Transactional
    fun delete(id: Long) = orderRepository.deleteById(id)
}
