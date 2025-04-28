package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.dto.ProductRequest;
import com.apeng.anticounterfeitbackend.entity.Goods;
import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.entity.QueryInfo;
import com.apeng.anticounterfeitbackend.repository.GoodsRepository;
import com.apeng.anticounterfeitbackend.repository.ProductRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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
        product.setSignature(generateSignature(product.getUuid()));
        product.setAntiCounterfeitingColors(acColorService.randomPick(6));
        return product;
    }

    private static String generateSignature(UUID uuid) {
        return DigestUtils.sha256Hex(uuid.toString() + privateKey);
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
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow();
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

    @Override
    public Product validate(UUID uuid, String signature, QueryInfo queryInfo) {
        validateSignature(uuid, signature);
        Product product = findProductByUuid(uuid);
        if (product != null) {
            updateProductStates(product, queryInfo);
            return productRepository.save(product);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private static void updateProductStates(Product product, QueryInfo queryInfo) {
        addQueryInfo2Product(product, queryInfo);
        product.increaseValidationCount();
        updateCounterfeitField(product);
    }

    private static void addQueryInfo2Product(Product product, QueryInfo queryInfo) {
        product.getQueries().add(queryInfo);
    }

    private Product findProductByUuid(UUID uuid) {
        return productRepository.findFirstByUuid(uuid);
    }

    private static void updateCounterfeitField(Product product) {
        if (product.getValidationCount() > 5) {
            product.setIsCounterfeit(true);
        }
    }

    private static void validateSignature(UUID uuid, String signature) {
        if (!isSignatureCorrect(uuid, signature)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid signature");
        }
    }

    private static boolean isSignatureCorrect(UUID uuid, String signature) {
        String correctSignature = generateSignature(uuid);
        return correctSignature.equals(signature);
    }
}
