package com.imooc.mapper;

import com.imooc.model.Card;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface CardMapper extends Mapper<Card> {
     List<Map<String,Object>> getListDisplay(@Param(value = "card") Card card);
     List<Map<String,Object>> getListDisplaySwagger(@Param(value = "card") Card card);
}
