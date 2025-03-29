package com.apeng.anticounterfeitbackend.repository;

import com.apeng.anticounterfeitbackend.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GoodsRepository extends JpaRepository<Goods, String> {
}
