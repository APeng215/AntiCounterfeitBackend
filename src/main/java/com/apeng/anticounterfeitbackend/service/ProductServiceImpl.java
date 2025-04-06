package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product add(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Product set(Product product) {
        return repository.save(product);
    }
}
