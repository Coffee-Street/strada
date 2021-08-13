package com.wnsgml972.strada.api.v1.banner.service

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
            .sortedBy { it.evalOrder }
            .map { it.toDto() }

    @Transactional(readOnly = true)
    fun select(code: String): BannerDTO =
        bannerRepository
            .findByCode(code)
            .orElseThrow { NotFoundException("$code Not Found") }
            .toDto()

    @Transactional
    fun insert(bannerInsertRequest: BannerInsertRequest): BannerInsertResponse =
        bannerRepository
            .save(bannerInsertRequest.toEntity())
            .toBannerInsertResponse()

    @Transactional
    fun update(bannerInsertRequest: BannerInsertRequest) =
        bannerRepository
            .findByCode(bannerInsertRequest.code)
            .orElseThrow { NotFoundException("${bannerInsertRequest.code} Not Found") }
            .id?.let {
                bannerRepository
                    .save(bannerInsertRequest.toEntity(it))
                    .toDto()
            }

    @Transactional
    fun delete(code: String) =
        bannerRepository
            .findByCode(code)
            .orElseThrow { NotFoundException("$code Not Found") }
            .run {
                bannerRepository.delete(this)
            }

    companion object : KLogging()
}
