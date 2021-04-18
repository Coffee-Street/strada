package com.wnsgml972.strada.api.v1.item.bread.service

import com.wnsgml972.strada.api.v1.item.bread.domain.BreadRepository
import com.wnsgml972.strada.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BreadService(
    private val breadRepository: BreadRepository
) {

    @Transactional(readOnly = true)
    fun selectAll(): List<BreadDTO> = breadRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: String): BreadDTO? = breadRepository.findByIdOrNull(id)?.toDto() ?: throw NotFoundException()

    @Transactional
    fun insert(breadDTO: BreadDTO) = breadRepository.save(breadDTO.toEntity())

    @Transactional
    fun update(breadDTO: BreadDTO) = breadRepository.save(breadDTO.toEntity())

    @Transactional
    fun delete(id: String) = breadRepository.deleteById(id)
}
