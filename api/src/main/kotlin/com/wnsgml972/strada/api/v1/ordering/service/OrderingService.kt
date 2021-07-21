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
    fun selectAll(): List<OrderingDTO> {
        return orderingRepository.findAll().map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun selectById(id: Long): OrderingDTO {
        return orderingRepository.findByIdOrNull(id)?.toDto() ?: throw NotFoundException("$id is not found")
    }

    @Transactional
    fun insert(orderingDTO: OrderingDTO): OrderingDTO {
        return orderingRepository.save(orderingDTO.toEntity()).toDto()
    }

    @Transactional
    fun update(orderingDTO: OrderingDTO): OrderingDTO {
        return orderingRepository.findById(orderingDTO.id)
            .orElseThrow { NotFoundException("${orderingDTO.id} is not found") }
            .let { orderingRepository.save(orderingDTO.toEntity()).toDto() }
    }

    @Transactional
    fun delete(id: Long) {
        orderingRepository.findById(id)
            .orElseThrow { NotFoundException("$id is not found") }
            .run { orderingRepository.delete(this) }
    }
}
