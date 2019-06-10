package com.imooc.service.impl;

import com.imooc.mapper.CardOccupationMapper;
import com.imooc.model.CardOccupation;
import com.imooc.service.CardOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardOccupationServiceImpl implements CardOccupationService {
    @Autowired
    CardOccupationMapper cardOccupationMapper;

    @Override
    public List<CardOccupation> getList(CardOccupation cardOccupation) {
        return cardOccupationMapper.select(cardOccupation);
    }
}
