package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.ordering.domain.OrderingRepository
import com.wnsgml972.strada.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderingService(
    private val orderingRepository: OrderingRepository
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<OrderingDTO> = orderingRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: Long): OrderingDTO? = orderingRepository.findByIdOrNull(id)?.toDto() ?: throw NotFoundException()

    @Transactional
    fun insert(orderingDTO: OrderingDTO) = orderingRepository.save(orderingDTO.toEntity())

    @Transactional
    fun update(orderingDTO: OrderingDTO) = orderingRepository.save(orderingDTO.toEntity())

    @Transactional
    fun delete(id: Long) = orderingRepository.findById(id).orElseThrow { NotFoundException("$id is not found") }.run { orderingRepository.delete(this) }
}
