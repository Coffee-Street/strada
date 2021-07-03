package com.wnsgml972.strada.api.v1.ordering.service

import com.wnsgml972.strada.api.v1.ordering.domain.OrderingDetailRepository
import com.wnsgml972.strada.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderingDetailService(
    private val orderingDetailRepository: OrderingDetailRepository
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<OrderingDetailDTO> {
        return orderingDetailRepository.findAll().map { it.toDto() }
    }

    @Transactional(readOnly = true)
    fun selectById(id: Long): OrderingDetailDTO {
        return orderingDetailRepository.findByIdOrNull(id)?.toDto() ?: throw NotFoundException("$id is not found")
    }

    @Transactional
    fun insert(orderingDetailDTO: OrderingDetailDTO): OrderingDetailDTO {
        return orderingDetailRepository.save(orderingDetailDTO.toEntity()).toDto()
    }

    @Transactional
    fun update(orderingDetailDTO: OrderingDetailDTO): OrderingDetailDTO {
        return orderingDetailRepository.findById(orderingDetailDTO.id)
            .orElseThrow { NotFoundException("${orderingDetailDTO.id} is not found") }
            .let { orderingDetailRepository.save(orderingDetailDTO.toEntity()).toDto() }
    }

    @Transactional
    fun delete(id: Long) {
        orderingDetailRepository.findById(id)
            .orElseThrow { NotFoundException("$id is not found") }
            .run { orderingDetailRepository.delete(this) }
    }
}
