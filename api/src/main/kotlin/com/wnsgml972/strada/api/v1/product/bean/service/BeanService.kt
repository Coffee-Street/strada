package com.wnsgml972.strada.api.v1.product.bean.service

import com.wnsgml972.strada.api.v1.product.bean.domain.BeanRepository
import com.wnsgml972.strada.exception.NotFoundException
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BeanService(
    private val beanRepository: BeanRepository,
) {

    @Transactional(readOnly = true)
    fun selectAll(): List<BeanDTO> =
        beanRepository
            .findAll()
            .map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: String): BeanDTO? =
        beanRepository
            .findById(id)
            .orElseThrow { NotFoundException("$id Not Found") }
            .toDto()

    @Transactional
    fun insert(beanDTO: BeanDTO) =
        beanRepository
            .save(beanDTO.toEntity())

    @Transactional
    fun update(beanDTO: BeanDTO) =
        beanRepository
            .save(beanDTO.toEntity())

    @Transactional
    fun delete(id: String) =
        beanRepository
            .findById(id)
            .orElseThrow { NotFoundException("$id Not Found") }
            .run { beanRepository.delete(this) }

    companion object : KLogging()
}
