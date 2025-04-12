package com.apeng.anticounterfeitbackend.service;


import com.apeng.anticounterfeitbackend.entity.Product;

import java.awt.*;
import java.util.List;

public interface ProductService {
    Product add(Product product);
    void deleteById(Long id);
    List<Product> getAll();
    Product getById(Long id);
    Product update(Long id, Product product);
    Product bindColors(Long id, List<Color> colors);
}
