package com.wnsgml972.strada.api.v1.item.service

import com.wnsgml972.strada.api.v1.item.domain.BreadRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BreadService(
    private val breadRepository: BreadRepository
) {

    fun selectAll(): List<BreadDTO> {
        val it = breadRepository.findAll()
        val breadDTO = ArrayList<BreadDTO>()
        it.forEach { v -> breadDTO.add(v.toDto()) }

        return breadDTO
    }

    fun selectById(id: String): BreadDTO? = breadRepository.findByIdOrNull(id)?.toDto()

    @Transactional
    fun insert(breadDTO: BreadDTO) = breadRepository.save(breadDTO.toEntity())
}
