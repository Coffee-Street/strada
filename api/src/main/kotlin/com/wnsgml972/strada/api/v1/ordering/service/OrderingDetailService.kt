package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetailRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderingDetailService(
    private val orderingDetailRepository: OrderingDetailRepository
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<OrderingDetailDTO> {
        return orderingDetailRepository
            .findAll()
            .map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun selectById(id: Long): OrderingDetailDTO {
        return orderingDetailRepository
            .findByIdOrNull(id)?.toDto() ?: throw StradaNotFoundException("$id is not found")
    }

    @Transactional
    fun insert(orderingDetailRequest: OrderingDetailRequest): OrderingDetailDTO {
        return orderingDetailRepository
            .save(orderingDetailRequest.toEntity())
            .toDto()
    }

    @Transactional
    fun update(id: Long, orderingDetailRequest: OrderingDetailRequest): OrderingDetailDTO {
        return orderingDetailRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id is not found") }
            .let { orderingDetailRepository.save(orderingDetailRequest.toEntity(id)).toDto() }
    }

    @Transactional
    fun delete(id: Long) {
        orderingDetailRepository.findById(id)
            .orElseThrow { StradaNotFoundException("$id is not found") }
            .run { orderingDetailRepository.delete(this) }
    }
}
