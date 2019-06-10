package com.imooc.service.impl;

import com.imooc.mapper.CardSetMapper;
import com.imooc.model.CardSet;
import com.imooc.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardSetServiceImpl implements CardSetService {
    @Autowired
    CardSetMapper cardSetMapper;

    @Override
    public List<CardSet> getList(CardSet cardSet) {
        return cardSetMapper.select(cardSet);
    }
}
