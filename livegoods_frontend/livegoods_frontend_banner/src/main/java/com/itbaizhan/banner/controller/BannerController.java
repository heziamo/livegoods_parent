package com.itbaizhan.banner.controller;

import com.itbaizhan.banner.service.BannerService;
import com.itbaizhan.commons.vo.LivegoodsResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Banner控制器
 */
// @RestController = @Controller + @ResponseBody
@RestController
public class BannerController {
    @DubboReference
    private BannerService bannerService;


    @GetMapping("/banner")
    public LivegoodsResult banner(){
        return bannerService.showBanner();
    }
}

