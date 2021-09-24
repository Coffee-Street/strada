package com.wnsgml972.strada.api.v1.profile.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.profile.service.ProfileRequest
import com.wnsgml972.strada.api.v1.profile.service.ProfileService
import com.wnsgml972.strada.api.v1.profile.service.toDto
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(path = [ProfileController.PROFILE_BASE_URL])
@Tag(
    name = "profiles",
    description = """User profile을 위한 API"""
)
class ProfileController @Autowired constructor(
    private var profileService: ProfileService
) {

    @GetMapping
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "모든 유저의 profile 가져오기")
    fun selectAll() =
        profileService.selectAll()

    @GetMapping("/{id}")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저의 profile 가져오기"),
        ApiResponse(responseCode = "401", description = "유저의 profile 인증 실패"),
        ApiResponse(responseCode = "403", description = "유저의 profile 접근 금지"),
    )
    fun select(@PathVariable("id") id: Long) =
        profileService.selectById(id)

    @PostMapping()
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저 profile 생성"),
        ApiResponse(responseCode = "304", description = "유저 profile 이미 존재"),
        ApiResponse(responseCode = "400", description = "유저 profile 생성 실패"),
    )
    fun insert(@RequestBody @Valid profileRequest: ProfileRequest) =
        profileService.insert(profileRequest.toDto())

    @PutMapping("/{id}")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저의 profile 수정"),
        ApiResponse(responseCode = "401", description = "유저의 profile 인증 실패"),
        ApiResponse(responseCode = "403", description = "유저의 profile 접근 금지"),
    )
    fun update(@PathVariable("id") id: Long, @RequestBody @Valid profileRequest: ProfileRequest) =
        profileService.update(profileRequest.toDto(id))

    @DeleteMapping("/{id}")
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저의 profile 삭제"),
        ApiResponse(responseCode = "401", description = "유저의 profile 인증 실패"),
        ApiResponse(responseCode = "403", description = "유저의 profile 접근 금지"),
    )
    fun delete(@PathVariable("id") id: Long) =
        profileService.delete(id)

    companion object : KLogging() {
        private const val PROFILE_SERVICE_NAME = "/profiles"
        const val PROFILE_BASE_URL = "$BASE_URL_V1/$PROFILE_SERVICE_NAME"
    }
}