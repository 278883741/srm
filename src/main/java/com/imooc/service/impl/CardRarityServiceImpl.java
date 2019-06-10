package com.imooc.service.impl;

import com.imooc.mapper.CardRarityMapper;
import com.imooc.model.CardRarity;
import com.imooc.service.CardRarityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardRarityServiceImpl implements CardRarityService {
    @Autowired
    CardRarityMapper cardRarityMapper;

    @Override
    public List<CardRarity> getList(CardRarity cardRarity) {
        return cardRarityMapper.select(cardRarity);
    }
}
