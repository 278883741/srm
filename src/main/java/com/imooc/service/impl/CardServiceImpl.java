package com.imooc.service.impl;

import com.imooc.mapper.CardMapper;
import com.imooc.model.Card;
import com.imooc.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardMapper cardMapper;

    @Override
    public boolean add(Card card) {
        return cardMapper.insertSelective(card) > 0;
    }

    @Override
    public Card get(Card card) {
        List<Card> list = cardMapper.select(card);
        if(list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Card> getList(Card card) {
        return cardMapper.select(card);
    }

    @Override
    public List<Map<String,Object>> getListDisplay(Card card) {
        return cardMapper.getListDisplay(card);
    }

    @Override
    public List<Map<String, Object>> getListDisplaySwagger(Card card) {
        return cardMapper.getListDisplaySwagger(card);
    }

    @Override
    public List<Card> getListByPage(Card card) {
        return null;
    }

    @Override
    public Integer update(Card card) {
        return cardMapper.updateByPrimaryKeySelective(card);
    }
}
