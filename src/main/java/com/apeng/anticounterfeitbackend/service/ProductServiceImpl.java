package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

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
    public Product update(Long id, Product product) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException();
        }
        product.setID(id);
        return repository.save(product);
    }

    @Transactional
    @Override
    public Product bindColors(Long id, List<Color> colors) {
        repository.findById(id).ifPresentOrElse(
                product -> {
                        product.setAntiCounterfeitingColors(colors);
                        repository.save(product);
                    },
                () -> {throw new NoSuchElementException(String.format("Cannot find product(id: %d)! You could create one first.", id));}
        );
        return repository.findById(id).get();
    }
}
