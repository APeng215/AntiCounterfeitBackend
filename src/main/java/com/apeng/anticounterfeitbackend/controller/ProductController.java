package com.apeng.anticounterfeitbackend.controller;

import com.apeng.anticounterfeitbackend.dto.ProductRequest;
import com.apeng.anticounterfeitbackend.dto.ProductResponse;
import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public List<Product> add(@RequestBody ProductRequest productRequest) {
        validateEmpty(productRequest);
        return productService.add(productRequest);
    }

    private static void validateEmpty(ProductRequest productRequest) {
        if (productRequest.hasNullField()) {
            throw new IllegalArgumentException("Product request has empty field!");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll().stream().map(ProductResponse::new).toList();
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @PatchMapping("/{id}")
    public Product bindColors(@PathVariable Long id, @RequestBody List<String> colorsInHex) {
        List<Color> colors = convert2ColorObjects(colorsInHex);
        return productService.bindColors(id, colors);
    }

    private static List<Color> convert2ColorObjects(List<String> colorsInHex) {
        return colorsInHex.stream().map(colorHex -> new Color(hexToPackedRgb(colorHex))).toList();
    }

    public static int hexToPackedRgb(String hexColor) {
        // Remove '#' if present
        String hex = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;

        // Validate length (6 characters)
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Invalid hex color format. Use #RRGGBB or RRGGBB.");
        }

        // Parse hex to RGB components
        int r = Integer.parseInt(hex.substring(0, 2), 16); // Red (bits 16-23)
        int g = Integer.parseInt(hex.substring(2, 4), 16); // Green (bits 8-15)
        int b = Integer.parseInt(hex.substring(4, 6), 16); // Blue (bits 0-7)

        // Combine into packed integer (0xRRGGBB)
        return (r << 16) | (g << 8) | b;
    }

}
