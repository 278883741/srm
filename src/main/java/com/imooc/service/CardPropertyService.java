package com.imooc.service;

import com.imooc.model.CardKind;
import com.imooc.model.CardProperty;

import java.util.List;

public interface CardPropertyService {
    List<CardProperty> getList(CardProperty cardProperty);
    CardProperty get(CardProperty cardProperty);
    boolean add(CardProperty cardProperty);
    boolean update(CardProperty cardProperty);
}
