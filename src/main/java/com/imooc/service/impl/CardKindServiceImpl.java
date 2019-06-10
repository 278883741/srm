package com.imooc.service.impl;

import com.imooc.mapper.CardKindMapper;
import com.imooc.model.CardKind;
import com.imooc.service.CardKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardKindServiceImpl implements CardKindService {
    @Autowired
    CardKindMapper cardKindMapper;

    @Override
    public List<CardKind> getList(CardKind cardKind) {
        return cardKindMapper.select(cardKind);
    }
}
