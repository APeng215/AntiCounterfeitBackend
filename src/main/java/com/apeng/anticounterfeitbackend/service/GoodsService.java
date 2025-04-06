package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.Goods;

import java.util.List;

public interface GoodsService {
    Goods add(Goods goods);
    void deleteById(String goodsName);
    List<Goods> getAll();
    Goods get(String goodsName);
    Goods set(Goods goods);
}
