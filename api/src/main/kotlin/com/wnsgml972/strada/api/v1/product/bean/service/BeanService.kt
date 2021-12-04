package com.wnsgml972.strada.api.v1.product.bean.service

import com.wnsgml972.strada.api.v1.product.bean.domain.Bean
import com.wnsgml972.strada.api.v1.product.bean.domain.BeanRepository
import com.wnsgml972.strada.api.v1.product.bread.service.toEntity
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
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
    fun selectById(id: String): BeanDTO =
        load(id)
            .toDto()

    @Transactional
    fun insert(beanDTO: BeanDTO) =
        beanRepository
            .save(beanDTO.toEntity())
            .toDto()

    @Transactional
    fun update(beanDTO: BeanDTO) =
        load(beanDTO.id)
            .let {
                beanRepository.save(beanDTO.toEntity())
            }
            .toDto()

    @Transactional
    fun delete(id: String) =
        load(id)
            .let {
                beanRepository.delete(it)
            }

    @Transactional(readOnly = true)
    fun load(id: String): Bean =
        beanRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }
    companion object : KLogging()
}
