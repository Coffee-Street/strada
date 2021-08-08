package com.wnsgml972.strada.api.v1.banner.service

import com.wnsgml972.strada.api.v1.banner.domain.Banner
import com.wnsgml972.strada.api.v1.banner.domain.BannerRepository
import javassist.NotFoundException
import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BannerService(
    private val bannerRepository: BannerRepository,
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<BannerDTO> =
        bannerRepository
            .findAll()
            .map { it.toDto() }
            .sortedBy { it.evalOrder }

    @Transactional(readOnly = true)
    fun select(evalOrder: Int): BannerDTO =
        bannerRepository
            .findByEvalOrder(evalOrder)
            .orElseThrow { NotFoundException("$evalOrder Not Found") }
            .toDto()

    @Transactional
    fun insert(bannerDTO: BannerDTO): Banner =
        bannerRepository
            .save(bannerDTO.toEntity())

    @Transactional
    fun update(bannerDTO: BannerDTO) =
        bannerRepository
            .findByEvalOrder(bannerDTO.evalOrder)
            .orElseThrow { NotFoundException("${bannerDTO.evalOrder} Not Found") }
            .id?.let {
                bannerRepository
                    .save(bannerDTO.toEntity(it))
            }

    @Transactional
    fun delete(evalOrder: Int) =
        bannerRepository
            .findByEvalOrder(evalOrder)
            .orElseThrow { NotFoundException("$evalOrder Not Found") }
            .run { bannerRepository.delete(this) }

    companion object : KLogging()
}