package com.imooc.service.impl;

import com.imooc.mapper.CardTypeMapper;
import com.imooc.model.CardType;
import com.imooc.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CardTypeServiceImpl implements CardTypeService {
    @Autowired
    CardTypeMapper cardTypeMapper;

    @Override
    public List<CardType> getList(CardType cardType) {
        return cardTypeMapper.select(cardType);
    }
}
