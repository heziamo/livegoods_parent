package com.itbaizhan.pojo;

import lombok.Data;

@Data
public class Product {
    //主键
    private Long id;
    //标题
    private String title;
    //图片路径，不包含Nginx地址
    private String img;
    //城市
    private String city;
    //销量
    private Long sales;
    //点击后的跳转
    private String link;
    //是否为热卖
    private Boolean hot;
    //热卖排序
    private Integer hotSort;

}
