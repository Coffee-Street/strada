package com.wnsgml972.strada.api.banner

import com.wnsgml972.strada.api.v1.banner.service.BannerInsertRequest
import com.wnsgml972.strada.api.v1.banner.service.BannerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class BannerHelper @Autowired constructor(
    private val bannerService: BannerService,
){

    @Transactional
    fun insertBanner(code:String, bannerInsertRequest: BannerInsertRequest) =
        bannerService.insert(code,bannerInsertRequest)

    @Transactional
    fun deleteBanner(code: String) =
        bannerService.delete(code)

    @Transactional
    fun clearBanner() =
        bannerService
            .selectAll()
            .forEach{
                bannerService
                    .delete(it.code)
            }

}