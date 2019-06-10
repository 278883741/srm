package com.imooc.service.impl;

import com.imooc.mapper.CardKindMapper;
import com.imooc.mapper.CardPropertyMapper;
import com.imooc.model.CardKind;
import com.imooc.model.CardProperty;
import com.imooc.service.CardKindService;
import com.imooc.service.CardPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardPropertyServiceImpl implements CardPropertyService {
    @Autowired
    CardPropertyMapper cardPropertyMapper;

    @Override
    public List<CardProperty> getList(CardProperty cardProperty) {
        return cardPropertyMapper.select(cardProperty);
    }

    @Override
    public CardProperty get(CardProperty cardProperty) {
        List<CardProperty> list = cardPropertyMapper.select(cardProperty);
        if(list != null && list.size() >0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean add(CardProperty cardProperty) {
        return cardPropertyMapper.insertSelective(cardProperty) > 0;
    }

    @Override
    public boolean update(CardProperty cardProperty) {
        return cardPropertyMapper.updateByPrimaryKeySelective(cardProperty) > 0;
    }
}
