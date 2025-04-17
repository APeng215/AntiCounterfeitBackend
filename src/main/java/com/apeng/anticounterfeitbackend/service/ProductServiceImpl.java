package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.dto.ProductRequest;
import com.apeng.anticounterfeitbackend.entity.Goods;
import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.repository.GoodsRepository;
import com.apeng.anticounterfeitbackend.repository.ProductRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    public static String privateKey = "c8f01010-9165-41d4-a217-82d500e9cd62"; // Random UUID

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private AcColorService acColorService;

    @Transactional
    @Override
    public List<Product> add(ProductRequest productRequest) {
        Goods goods2Bind = goodsRepository.findById(productRequest.getGoodsName()).orElseThrow();
        List<Product> products = generateProducts(productRequest, goods2Bind);
        return productRepository.saveAll(products);
    }

    private List<Product> generateProducts(ProductRequest productRequest, Goods goods2Bind) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < productRequest.getProduceNum(); i++) {
            Product product = generateSingleProduct(productRequest, goods2Bind);
            result.add(product);
        }
        return result;
    }

    private Product generateSingleProduct(ProductRequest productRequest, Goods goods2Bind) {
        Product product = new Product(goods2Bind, productRequest.getProduceDate());
        product.initUUID();
        product.setSignature(DigestUtils.sha256Hex(product.getUuid().toString() + privateKey));
        product.setAntiCounterfeitingColors(acColorService.randomPick(5));
        return product;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product update(Long id, Product product) {
        throw new UnsupportedOperationException("Update for product is unsupported!");
    }

    @Transactional
    @Override
    public Product bindColors(Long id, List<Color> colors) {
        productRepository.findById(id).ifPresentOrElse(
                product -> {
                        product.setAntiCounterfeitingColors(colors);
                        productRepository.save(product);
                    },
                () -> {throw new NoSuchElementException(String.format("Cannot find product(id: %d)! You could create one first.", id));}
        );
        return productRepository.findById(id).get();
    }
}
