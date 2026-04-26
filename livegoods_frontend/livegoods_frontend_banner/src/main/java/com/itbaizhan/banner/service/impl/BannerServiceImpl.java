package com.itbaizhan.banner.service.impl;

import com.itbaizhan.banner.mapper.BannerMapper;
import com.itbaizhan.banner.service.BannerService;
import com.itbaizhan.commons.vo.LivegoodsResult;
import com.itbaizhan.pojo.Banner;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
/*
@DubboService会发布当前类所实现的接口
 */
@DubboService
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public LivegoodsResult showBanner() {
        List<Banner> list = bannerMapper.selectShowBanner();
        List<String> imgs = new ArrayList<>();

        list.forEach(banner -> {
            imgs.add(banner.getUrl());
        });
        return LivegoodsResult.ok(imgs);
    }
}

