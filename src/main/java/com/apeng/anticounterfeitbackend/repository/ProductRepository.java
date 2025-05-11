package com.apeng.anticounterfeitbackend.repository;

import com.apeng.anticounterfeitbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByUuid(UUID uuid);
}
