package com.apeng.anticounterfeitbackend.service;


import com.apeng.anticounterfeitbackend.dto.ProductRequest;
import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.entity.QueryInfo;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> add(ProductRequest productRequest);
    void deleteById(Long id);
    List<Product> getAll();
    Product get(Long id);
    Product getById(Long id);
    Product update(Long id, Product product);
    Product bindColors(Long id, List<Color> colors);
    Product validate(UUID uuid, String signature, QueryInfo queryInfo);
    void deleteAll(List<Long> ids);
}
