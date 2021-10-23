package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.ordering.domain.OrderingRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderingService(
    private val orderingRepository: OrderingRepository
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
        return orderingRepository
            .save(orderingRequest.toEntity())
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
