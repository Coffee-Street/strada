package com.wnsgml972.strada.api.v1.item.bread.service

import com.wnsgml972.strada.api.v1.item.bread.domain.BreadRepository
import com.wnsgml972.strada.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BreadBannerService(
    private val breadRepository: BreadRepository
) {

    @Transactional(readOnly = true)
    fun selectById(id: String): BreadBannerDTO? =
        breadRepository.findByIdOrNull(id)?.toBannerDto() ?: throw NotFoundException("$id Not Found")
}