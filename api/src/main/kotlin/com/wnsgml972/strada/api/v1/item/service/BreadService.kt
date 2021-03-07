package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.BreadRepository
import com.wnsgml972.strada.api.v1.item.domain.Coffee
import com.wnsgml972.strada.api.v1.item.domain.CoffeeRepository
import com.wnsgml972.strada.api.v1.item.domain.NonCoffeeRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class BreadService (
    private val breadRepository: BreadRepository
){

    fun findAll(): List<BreadDTO> {
        val it = breadRepository.findAll()
        val breadDTO = ArrayList<BreadDTO>()
        it.forEach { v -> breadDTO.add(v.toDto()) }

        return breadDTO
    }

    fun findById(id: String) : BreadDTO? = breadRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun save(breadDTO: BreadDTO)  = breadRepository.save(breadDTO.toEntity())

}