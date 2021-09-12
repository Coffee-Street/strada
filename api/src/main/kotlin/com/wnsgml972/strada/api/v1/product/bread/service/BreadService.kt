package com.wnsgml972.strada.api.v1.item.bread.service

import com.wnsgml972.strada.api.v1.product.bread.domain.BreadRepository
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.bread.service.toDto
import com.wnsgml972.strada.api.v1.product.bread.service.toEntity
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import mu.KLogging

@Service
class BreadService(
    private val breadRepository: BreadRepository,
) {

    @Transactional(readOnly = true)
    fun selectAll() =
        breadRepository
            .findAll()
            .map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: String) =
        breadRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw StradaNotFoundException("$id is not found")

    @Transactional
    fun insert(breadDTO: BreadDTO) =
        breadRepository
            .save(breadDTO.toEntity())

    @Transactional
    fun update(breadDTO: BreadDTO) =
        breadRepository
            .findById(breadDTO.id)
            .orElseThrow { StradaNotFoundException("${breadDTO.id} is not found") }
            .let {
                breadRepository.save(breadDTO.toEntity())
            }

    @Transactional
    fun delete(id: String) =
        breadRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id is not found") }
            .let {
                breadRepository.delete(it)
            }

    companion object : KLogging()
}
