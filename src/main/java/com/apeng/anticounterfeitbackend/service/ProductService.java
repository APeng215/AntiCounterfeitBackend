package com.apeng.anticounterfeitbackend.service;


import com.apeng.anticounterfeitbackend.dto.ProductRequest;
import com.apeng.anticounterfeitbackend.entity.Product;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product add(ProductRequest productRequest);
    void deleteById(Long id);
    List<Product> getAll();
    Product getById(Long id);
    Product update(Long id, Product product);
    Product bindColors(Long id, List<Color> colors);
}
