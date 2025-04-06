package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.Goods;
import com.apeng.anticounterfeitbackend.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsRepository repository;

    @Override
    public Goods add(Goods goods) {
        return repository.save(goods);
    }

    @Override
    public void deleteById(String goodsName) {
        repository.deleteById(goodsName);
    }

    @Override
    public List<Goods> getAll() {
        return repository.findAll();
    }

    @Override
    public Goods get(String goodsName) {
        return repository.findById(goodsName).orElseThrow();
    }

    @Override
    public Goods set(Goods goods) {
        return repository.save(goods);
    }
}
