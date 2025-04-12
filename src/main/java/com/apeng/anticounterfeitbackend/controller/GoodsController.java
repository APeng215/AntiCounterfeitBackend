package com.apeng.anticounterfeitbackend.controller;

import com.apeng.anticounterfeitbackend.entity.Goods;
import com.apeng.anticounterfeitbackend.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping
    public Goods add(@RequestBody Goods goods) {
        return goodsService.add(goods);
    }

    @DeleteMapping("/{goodsName}")
    public void delete(@PathVariable String goodsName) {
        goodsService.deleteById(goodsName);
    }

    @GetMapping
    public List<Goods> getAll() {
        return goodsService.getAll();
    }

    @PutMapping
    public Goods set(@RequestBody Goods goods) {
        return goodsService.update(goods);
    }

}
