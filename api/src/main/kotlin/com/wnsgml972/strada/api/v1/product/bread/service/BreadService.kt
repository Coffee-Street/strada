package com.wnsgml972.strada.api.v1.item.bread.service

import com.wnsgml972.strada.api.v1.product.bread.domain.Bread
import com.wnsgml972.strada.api.v1.product.bread.domain.BreadRepository
import com.wnsgml972.strada.api.v1.product.bread.service.BreadDTO
import com.wnsgml972.strada.api.v1.product.bread.service.toDto
import com.wnsgml972.strada.api.v1.product.bread.service.toEntity
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
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
        load(id)
            .toDto()

    @Transactional
    fun insert(breadDTO: BreadDTO) =
        breadRepository
            .save(breadDTO.toEntity())
            .toDto()

    @Transactional
    fun update(breadDTO: BreadDTO) =
        load(breadDTO.id)
            .let {
                breadRepository.save(breadDTO.toEntity())
            }

    @Transactional
    fun delete(id: String) =
        load(id)
            .let {
                breadRepository.delete(it)
            }

    @Transactional(readOnly = true)
    fun load(id: String): Bread =
        breadRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }

    companion object : KLogging()
}
