package com.apeng.anticounterfeitbackend.controller;

import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product add(@RequestBody Product Product) {
        return productService.add(Product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PutMapping
    public Product set(@RequestBody Product Product) {
        return productService.set(Product);
    }

}
