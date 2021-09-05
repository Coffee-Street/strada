//package com.wnsgml972.strada.api.v1.banner
//
//import com.wnsgml972.strada.api.v1.banner.service.BannerInsertRequest
//import com.wnsgml972.strada.api.v1.banner.service.BannerService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.ApplicationArguments
//import org.springframework.boot.ApplicationRunner
//import org.springframework.stereotype.Component
//
//@Component
//class BannerRunner @Autowired constructor(
//    private val bannerService: BannerService
//) : ApplicationRunner {
//
//    @SuppressWarnings("MagicNumber")
//    override fun run(args: ApplicationArguments?) {
//        val first = BannerInsertRequest(
//            1,
//            "날씨도 쌀쌀한데 따뜻한 캐모마일 어떠세요? ",
//            "http://coffee-image-1",
//            "유자캐모마일"
//        )
//        val second = BannerInsertRequest(
//            2,
//            "여기커피 맛있어요! ",
//            "http://coffee-image-2",
//            "아메리카노"
//        )
//        val third = BannerInsertRequest(
//            3,
//            "달콤한 케이크 추천해요! ",
//            "http://coffee-image-3",
//            "초콜릿 케이크"
//        )
//
//        bannerService.insert("first banner", first)
//        bannerService.insert("second banner", third)
//        bannerService.insert("third banner", second)
//    }
//}
