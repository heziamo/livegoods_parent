package com.itbaizhan.hot.mapper;

import com.itbaizhan.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotMapper {
    /**
     * 查询销量最高的四个产品
     * @param city
     * @return
     */
    List<Product> selectSales(String city);
}

