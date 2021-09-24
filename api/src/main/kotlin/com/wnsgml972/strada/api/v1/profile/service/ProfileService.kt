package com.wnsgml972.strada.api.v1.profile.service

import com.wnsgml972.strada.api.v1.profile.domain.ProfileRepository
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileService(
    private val profileRepository: ProfileRepository
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<ProfileDTO> =
        profileRepository
            .findAll()
            .map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: Long): ProfileDTO =
        profileRepository
            .findByIdOrNull(id)
            ?.toDto()
            ?: throw StradaNotFoundException("$id is not found")

    @Transactional
    fun insert(profileDTO: ProfileDTO) =
        profileRepository
            .save(profileDTO.toEntity())
            .toDto()

    @Transactional
    fun update(profileDTO: ProfileDTO) =
        profileRepository
            .findById(profileDTO.id)
            .orElseThrow { StradaNotFoundException("$profileDTO.id Not Found") }
            .let {
                profileRepository
                    .save(profileDTO.toEntity())
                    .toDto()
            }

    @Transactional
    fun delete(id: Long) =
        profileRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .run { profileRepository.delete(this) }
}
