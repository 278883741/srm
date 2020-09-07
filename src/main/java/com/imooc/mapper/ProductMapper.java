package com.imooc.mapper;

import com.imooc.model.Product;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ProductMapper extends Mapper<Product> {
    int updateTotal(@Param(value = "productNo") String productNo);
}