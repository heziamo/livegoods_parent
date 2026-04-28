package com.itbaizhan.hot.service.impl;

import com.itbaizhan.commons.redis.dao.RedisDao;
import com.itbaizhan.commons.vo.LivegoodsResult;
import com.itbaizhan.hot.mapper.HotMapper;
import com.itbaizhan.hot.service.HotService;
import com.itbaizhan.pojo.Product;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.concurrent.TimeUnit;

@DubboService
public class HotServiceImpl implements HotService {
    @Autowired
    private HotMapper hotMapper;
    @Value("${livegoods.nginx.host}")
    private String nginxHost;
    @Value("${livegoods.redis.hot}")
    private String hotKey;
    @Autowired
    private RedisDao redisDao;
    @Override
    public LivegoodsResult showHotSales(String city) {
        if(redisDao.hasKey(hotKey)){
            System.out.println("热销-缓存");
            return redisDao.get(hotKey);
        }
        System.out.println("热销-mysql");
        List<Product> list = hotMapper.selectSales(city);
        list.forEach(product -> {
            product.setImg(nginxHost+product.getImg());
        });
        LivegoodsResult lg = LivegoodsResult.ok(list);
        redisDao.set(hotKey,lg,7, TimeUnit.DAYS);
        return lg;
    }
}
