package com.imooc.service;

import com.imooc.model.RedDetail;

public interface RedDetailService {
    boolean add(RedDetail redDetail);

    RedDetail get(RedDetail redDetail);

    Integer update(RedDetail redDetail);
}
