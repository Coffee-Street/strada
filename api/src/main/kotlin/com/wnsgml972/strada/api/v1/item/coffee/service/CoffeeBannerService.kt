package com.wnsgml972.strada.api.v1.item.coffee.service

import com.wnsgml972.strada.api.v1.item.coffee.domain.CoffeeRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CoffeeBannerService(
    private val coffeeRepository: CoffeeRepository
) {
    @Transactional(readOnly = true)
    fun selectById(id: String): CoffeeBannerDTO? =
        coffeeRepository.findByIdOrNull(id)?.toBannerDto() ?: throw StradaNotFoundException("$id Not Found")
}
