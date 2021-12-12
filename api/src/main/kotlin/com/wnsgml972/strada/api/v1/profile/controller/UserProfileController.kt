package com.wnsgml972.strada.api.v1.profile.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.profile.service.UserProfileDTO
import com.wnsgml972.strada.api.v1.profile.service.UserProfileRequest
import com.wnsgml972.strada.api.v1.profile.service.UserProfileService
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import com.wnsgml972.strada.security.SecurityUtils
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(path = [UserProfileController.USER_PROFILE_BASE_URL])
@Tag(
    name = "profiles",
    description = """User profile을 위한 API"""
)
class UserProfileController @Autowired constructor(
    private var userProfileService: UserProfileService,
) {

//    @GetMapping
//    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
//    @ApiResponse(responseCode = "200", description = "모든 유저의 profile 가져오기")
//    fun selectAll() =
//        userProfileService.selectAll()

    @GetMapping
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "token에 있는 정보를 통해 해당 user의 profile을 가져오기")
    fun select(): UserProfileDTO =
        userProfileService.selectByUserId(SecurityUtils.getPrincipal().phoneNumber.number)

    @PostMapping
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저 profile 생성"),
        ApiResponse(responseCode = "304", description = "유저 profile 이미 존재"),
        ApiResponse(responseCode = "400", description = "유저 profile 생성 실패"),
    )
    fun insert(@RequestBody @Valid userProfileRequest: UserProfileRequest) =
        userProfileService.insert(userProfileRequest)

    @PutMapping
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저의 profile 수정"),
        ApiResponse(responseCode = "401", description = "유저의 profile 인증 실패"),
        ApiResponse(responseCode = "403", description = "유저의 profile 접근 금지"),
    )
    fun update(@RequestBody @Valid userProfileRequest: UserProfileRequest) =
        userProfileService.update(SecurityUtils.getPrincipal().phoneNumber.number, userProfileRequest)

    @DeleteMapping
    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "유저의 profile 삭제"),
        ApiResponse(responseCode = "401", description = "유저의 profile 인증 실패"),
        ApiResponse(responseCode = "403", description = "유저의 profile 접근 금지"),
    )
    fun delete() =
        userProfileService.delete(SecurityUtils.getPrincipal().phoneNumber.number)

    companion object : KLogging() {
        private const val USER_PROFILE_SERVICE_NAME = "/profiles"
        const val USER_PROFILE_BASE_URL = "$BASE_URL_V1/$USER_PROFILE_SERVICE_NAME"
    }
}
