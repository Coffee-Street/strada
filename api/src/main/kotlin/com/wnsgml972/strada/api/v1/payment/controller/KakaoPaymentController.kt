package com.wnsgml972.strada.api.v1.payment.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.payment.service.KakaoPaymentService
import com.wnsgml972.strada.api.v1.payment.service.PaymentInsertRequest
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
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
@RequestMapping(path = [KakaoPaymentController.USER_PROFILE_BASE_URL])
@Tag(
    name = "Payment",
    description = """Payment를 위한 API"""
)
class KakaoPaymentController @Autowired constructor(
    private val kakaoPaymentService: KakaoPaymentService
) {
    @GetMapping
    @Operation(
        summary = "모든 결제 가져오기",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "List all Payment")
    fun findAll() = kakaoPaymentService.selectAll()

    @GetMapping("/{id}")
    @Operation(
        summary = "특정 결제 가져오기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Find Payment")
    fun find(
        @PathVariable("id") id: Long
    ) =
        kakaoPaymentService.selectById(id)

    @PostMapping
    @Operation(
        summary = "결제하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Create Payment")
    fun insert(
        @RequestBody @Valid paymentInsertRequest: PaymentInsertRequest
    ) =
        kakaoPaymentService.insert(paymentInsertRequest)

    @PutMapping("/{id}")
    @Operation(
        summary = "결제 갱신하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Update Payment")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody @Valid paymentInsertRequest: PaymentInsertRequest
    ) =
        kakaoPaymentService.update(id, paymentInsertRequest)

    @DeleteMapping("/{id}")
    @Operation(
        summary = "결제 삭제하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)]
    )
    @ApiResponse(responseCode = "200", description = "Delete Payment")
    fun delete(
        @PathVariable("id") id: Long
    ) =
        kakaoPaymentService.delete(id)

//    @GetMapping("success/{pg_token}")
//    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
//    @ApiResponses(
// //        ApiResponse(responseCode = "200", description = "유저의 profile 가져오기"),
// //        ApiResponse(responseCode = "401", description = "유저의 profile 인증 실패"),
// //        ApiResponse(responseCode = "403", description = "유저의 profile 접근 금지"),
//    )
//    fun success(@PathVariable("pg_token") token: String): KakaoPayApprovalVo? {
//        logger.debug("pg_token : ", token)
//        return kakaoPaymentService.success(token)
//    }
//
//    @PostMapping("/ready")
//    @CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
//    @Operation(security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
//    fun ready(@RequestBody kakaoPaymentReadyVo: KakaoPaymentReadyVo) = kakaoPaymentService.ready(kakaoPaymentReadyVo)
//

    companion object : KLogging() {
        private const val KAKAO_PAYMENT_SERVICE_NAME = "/payment"
        const val USER_PROFILE_BASE_URL = "$BASE_URL_V1/$KAKAO_PAYMENT_SERVICE_NAME"
    }
}
