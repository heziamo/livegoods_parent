package com.itbaizhan.hot.controller;

import com.itbaizhan.commons.vo.LivegoodsResult;
import com.itbaizhan.hot.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotController {
    @Autowired
    private HotService hotService;

    @GetMapping("/hotProduct")
    public LivegoodsResult hotProduct(String city){
        return hotService.showHotSales(city);
    }
}
