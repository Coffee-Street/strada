package com.wnsgml972.strada.api.v1.payment.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.kakao.service.KakaoApiService
import com.wnsgml972.strada.api.v1.payment.service.KakaoPaymentService
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiReadyRequest
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiReadyResponse
import com.wnsgml972.strada.api.v1.payment.service.PaymentApproveRequest
import com.wnsgml972.strada.api.v1.payment.service.PaymentStatusUpdateRequest
import com.wnsgml972.strada.config.management.SpringdocOpenApiConfig
import com.wnsgml972.strada.security.SecurityUtils
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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import javax.validation.Valid

@RestController
@RequestMapping(path = [KakaoPaymentController.KAKAO_PAYMENT_BASE_URL])
@Tag(
    name = "Payment",
    description = """Payment를 위한 API"""
)
class KakaoPaymentController @Autowired constructor(
    private val kakaoPaymentService: KakaoPaymentService,
    private val kakaoApiService: KakaoApiService,
) {
    @GetMapping
    @Operation(
        summary = "모든 결제 가져오기",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "List all Payment")
    fun findAll() = kakaoPaymentService.selectAll()

    @GetMapping("/{id}")
    @Operation(
        summary = "특정 결제 가져오기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Find Payment")
    fun find(@PathVariable("id") id: Long) =
        kakaoPaymentService.selectById(id)

    @PostMapping("/approve/{id}")
    @Operation(
        summary = "결제하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Create Payment")
    fun approve(@PathVariable("id") id: Long, @RequestBody @Valid paymentApproveRequest: PaymentApproveRequest) =
        kakaoPaymentService.update(id, paymentApproveRequest)

    @PutMapping("/status/{id}")
    @Operation(
        summary = "결제 갱신하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Update Payment")
    fun updateStatus(
        @PathVariable("id") id: Long,
        @RequestBody @Valid paymentStatusUpdateRequest: PaymentStatusUpdateRequest,
    ) =
        kakaoPaymentService.updatePaymentStatus(id, paymentStatusUpdateRequest)

    @PutMapping("/{id}")
    @Operation(
        summary = "결제 갱신하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Create Payment")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody @Valid paymentApproveRequest: PaymentApproveRequest,
    ) =
        kakaoPaymentService.update(id, paymentApproveRequest)

    @DeleteMapping("/{id}")
    @Operation(
        summary = "결제 삭제하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Delete Payment")
    fun delete(@PathVariable("id") id: Long) =
        kakaoPaymentService.delete(id)

    @PostMapping("/readyPayment")
    @Operation(
        summary = "결제 ready",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Find Payment")
    fun readyPayment(
        @RequestBody @Valid kakaoRestApiReadyRequest: KakaoRestApiReadyRequest
    ): KakaoRestApiReadyResponse {

        return kakaoApiService.readyPayment(SecurityUtils.getPrincipal().phoneNumber.number, kakaoRestApiReadyRequest)
    }

    @GetMapping("/approve")
    @Operation(
        summary = "test")
    @ApiResponse(responseCode = "200", description = "Find Payment")
    fun approvePayment(@RequestParam("pg_token") pgToken: String, attributes: RedirectAttributes): RedirectView {

        logger.debug { "pg_token: $pgToken" }
        attributes.addAttribute("pg_token", pgToken)
        return RedirectView("http://www.naver.com")
    }

    companion object : KLogging() {
        private const val KAKAO_PAYMENT_SERVICE_NAME = "/payment"
        const val KAKAO_PAYMENT_BASE_URL = "$BASE_URL_V1/$KAKAO_PAYMENT_SERVICE_NAME"
    }
}
