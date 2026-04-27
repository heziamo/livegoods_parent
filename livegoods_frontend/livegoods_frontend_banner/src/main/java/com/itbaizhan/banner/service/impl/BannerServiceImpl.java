package com.itbaizhan.banner.service.impl;

import com.itbaizhan.banner.mapper.BannerMapper;
import com.itbaizhan.banner.service.BannerService;
import com.itbaizhan.commons.redis.dao.RedisDao;
import com.itbaizhan.commons.vo.LivegoodsResult;
import com.itbaizhan.pojo.Banner;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
@DubboService会发布当前类所实现的接口
 */
@DubboService
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Value("${livegoods.nginx.host}")
    private String nginxHost;
    //对Spring Data Redis的上层封装
    @Autowired
    private RedisDao redisDao;
    @Value("${livegoods.redis.banner}")
    private String bannerKey;
    @Override
    public LivegoodsResult showBanner() {
        //边路缓存思想：查询先查询Redis，没有再查询Mysql
        if(redisDao.hasKey(bannerKey)){
            System.out.println("banner走缓存");
            return redisDao.get(bannerKey);
        }
        //从数据库查询轮播图信息
        List<Banner> list = bannerMapper.selectShowBanner();
        //放所有图片的信息
        List<String> imgs = new ArrayList<>();
        //遍历URL放入imgs
        list.forEach(banner -> {
            imgs.add(nginxHost+banner.getUrl());
        });
        LivegoodsResult lg = LivegoodsResult.ok(imgs);
        redisDao.set(bannerKey,lg,7, TimeUnit.DAYS);
        return lg;
    }
}

