package com.wnsgml972.strada.api.v1.banner.service

import com.wnsgml972.strada.api.v1.banner.domain.BannerRepository
import com.wnsgml972.strada.exception.NotFoundException

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
    fun insert(code: String, bannerInsertRequest: BannerInsertRequest): BannerInsertResponse =
        bannerRepository
            .save(bannerInsertRequest.toEntity(code))
            .toBannerInsertResponse()

    @Transactional
    fun update(code: String, bannerInsertRequest: BannerInsertRequest) =
        bannerRepository
            .findByCode(code)
            .orElseThrow { NotFoundException("$code Not Found") }
            .id?.let {
                bannerRepository
                    .save(bannerInsertRequest.toEntity(it, code))
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
