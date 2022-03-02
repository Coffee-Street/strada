package com.wnsgml972.strada.api.v1.purchase.controller

import BASE_URL_V1
import com.wnsgml972.strada.api.v1.purchase.controller.PurchaseController.Companion.PURCHASE_BASE_URL
import com.wnsgml972.strada.api.v1.purchase.service.PurchaseDTO
import com.wnsgml972.strada.api.v1.purchase.service.PurchaseService
import com.wnsgml972.strada.security.SecurityUtils
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path= [PURCHASE_BASE_URL])
class PurchaseController @Autowired constructor(
    private var purchaseService: PurchaseService
) {

    @GetMapping
    fun selectAll(): List<PurchaseDTO> =
        purchaseService.findAll(SecurityUtils.getPrincipal().phoneNumber.number)

    companion object : KLogging() {
        private const val PURCHASE_SERVICE_NAME = "/purchase"
        const val PURCHASE_BASE_URL = "$BASE_URL_V1/$PURCHASE_SERVICE_NAME"
    }
}