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
        load(userId)
            .toDto()

    @Transactional
    fun insert(userProfileRequest: UserProfileRequest) =
        profileRepository
            .save(userProfileRequest.toEntity())
            .toDto()

    @Transactional
    fun update(userId: String, userProfileRequest: UserProfileRequest) =
        load(userId)
            .run {
                profileRepository
                    .save(userProfileRequest.toEntity(this.id))
                    .toDto()
            }

    @Transactional
    fun delete(userId: String) =
        load(userId)
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

    @Transactional
    private fun load(userId: String): UserProfile =
        profileRepository
            .findByUserId(userId)
            .orElseThrow { StradaNotFoundException("$userId's entity is Not Found") }
            .let {
                it.id ?: throw StradaIllegalStateException("${it.id} is not initialized")
                it
            }
}
