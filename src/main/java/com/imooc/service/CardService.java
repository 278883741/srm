package com.imooc.service;

import com.imooc.model.Card;
import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CardService {
    /**
     * 添加卡牌
     * @param card
     * @return
     */
    boolean add(Card card);

    /**
     * 获取卡牌
     * @param card
     * @return
     */
    Card get(Card card);

    /**
     * 获取卡牌列表
     * @param card
     * @return
     */
    List<Card> getList(Card card);

    /**
     * 获取卡牌列表 - 用于页面展示
     * @return
     */
    List<Map<String,Object>> getListDisplay(Card card);

    /**
     * 获取卡牌列表 - 用于页面展示
     * @return
     */
    List<Map<String,Object>> getListDisplaySwagger(Card card);

    /**
     * 分页获取卡牌列表
     * @param card
     * @return
     */
    List<Card> getListByPage(Card card);

    /**
     * 更新卡牌
     * @param card
     * @return
     */
    Integer update(Card card);
}
