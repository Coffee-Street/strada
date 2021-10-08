package com.wnsgml972.strada.api.v1.profile.service

import com.wnsgml972.strada.api.v1.profile.domain.UserProfile
import com.wnsgml972.strada.api.v1.profile.domain.UserProfileRepository
import com.wnsgml972.strada.exception.StradaIllegalStateException
import com.wnsgml972.strada.exception.StradaNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserProfileService(
    private val profileRepository: UserProfileRepository
) {
    @Transactional(readOnly = true)
    fun selectAll(): List<UserProfileDTO> =
        profileRepository
            .findAll()
            .map { it.toDto() }

    @Transactional(readOnly = true)
    fun selectById(id: Long): UserProfileDTO =
        load(id)
            .toDto()

    @Transactional(readOnly = true)
    fun selectByUserId(userId: String): UserProfileDTO =
        profileRepository
            .findByUserId(userId)
            .orElseThrow { StradaNotFoundException("$userId Not Found") }
            .toDto()

    @Transactional
    fun insert(userProfileRequest: UserProfileRequest) =
        profileRepository
            .save(userProfileRequest.toEntity())
            .toDto()

    @Transactional
    fun update(id: Long, userProfileRequest: UserProfileRequest) =
        load(id)
            .let {
                profileRepository
                    .save(userProfileRequest.toEntity(id))
                    .toDto()
            }

    @Transactional
    fun delete(id: Long) =
        load(id)
            .run {
                profileRepository.delete(this)
            }

    @Transactional(readOnly = true)
    private fun load(id: Long): UserProfile =
        profileRepository
            .findById(id)
            .orElseThrow { StradaNotFoundException("$id Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }
}
