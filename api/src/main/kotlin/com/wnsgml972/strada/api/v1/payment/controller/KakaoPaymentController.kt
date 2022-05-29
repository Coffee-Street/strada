package com.wnsgml972.strada.api.v1.payment.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.payment.service.KakaoPaymentService
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiApproveResponse
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiReadyRequest
import com.wnsgml972.strada.api.v1.payment.service.KakaoRestApiReadyResponse
import com.wnsgml972.strada.api.v1.payment.service.PaymentApproveRequest
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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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

    @DeleteMapping("/{id}")
    @Operation(
        summary = "결제 삭제하기 for test",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Delete Payment")
    fun delete(@PathVariable("id") id: Long) =
        kakaoPaymentService.delete(id)

    @PostMapping("/ready")
    @Operation(
        summary = "결제 ready",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "ready Payment")
    fun ready(
        @RequestBody @Valid kakaoRestApiReadyRequest: KakaoRestApiReadyRequest
    ): KakaoRestApiReadyResponse =
        kakaoPaymentService.readyPayment(SecurityUtils.getPrincipal().phoneNumber.number, kakaoRestApiReadyRequest)

    @GetMapping("/success")
    @Operation(summary = "redirect success test")
    @ApiResponse(responseCode = "200", description = "Redirect success case, return App Scheme URL")
    fun success(@RequestParam("pg_token") pgToken: String): RedirectView {
        return RedirectView("strada://payment/success?pg_token=$pgToken")
    }

    @GetMapping("/fail")
    @Operation(summary = "redirect fail test")
    @ApiResponse(responseCode = "200", description = "Redirect fail case")
    fun fail(): RedirectView {
        return RedirectView("strada://payment/fail")
    }

    @GetMapping("/cancel")
    @Operation(summary = "redirect cancel test")
    @ApiResponse(responseCode = "200", description = "Redirect cancel case")
    fun cancel(): RedirectView {
        return RedirectView("strada://payment/cancel")
    }
    @PostMapping("/approve")
    @Operation(
        summary = "결제 approve",
        security = [SecurityRequirement(name = SpringdocOpenApiConfig.OPEN_API_BEARER_KEY)])
    @ApiResponse(responseCode = "200", description = "Approve payment")
    fun approve(@RequestBody @Valid paymentApproveRequest: PaymentApproveRequest): KakaoRestApiApproveResponse =
        kakaoPaymentService.approvePayment(paymentApproveRequest)

    companion object : KLogging() {
        private const val KAKAO_PAYMENT_SERVICE_NAME = "/payments"
        const val KAKAO_PAYMENT_BASE_URL = "$BASE_URL_V1/$KAKAO_PAYMENT_SERVICE_NAME"
    }
}
