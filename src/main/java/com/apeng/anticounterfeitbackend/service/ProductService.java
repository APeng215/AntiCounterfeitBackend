package com.apeng.anticounterfeitbackend.service;


import com.apeng.anticounterfeitbackend.entity.Product;

import java.util.List;

public interface ProductService {
    Product add(Product product);
    void deleteById(Long id);
    List<Product> getAll();
    Product getById(Long id);
    Product set(Product product);
}
