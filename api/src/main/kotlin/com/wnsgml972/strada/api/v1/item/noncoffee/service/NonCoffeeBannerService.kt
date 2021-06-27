package com.wnsgml972.strada.api.v1.item.noncoffee.service

import com.wnsgml972.strada.api.v1.item.noncoffee.domain.NonCoffeeRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NonCoffeeBannerService(
    private val nonCoffeeRepository: NonCoffeeRepository
) {
    @Transactional(readOnly = true)
    fun selectById(id: String): NonCoffeeBannerDTO? =
        nonCoffeeRepository.findByIdOrNull(id)?.toBannerDto() ?: throw StradaNotFoundException("$id Not Found")
}
