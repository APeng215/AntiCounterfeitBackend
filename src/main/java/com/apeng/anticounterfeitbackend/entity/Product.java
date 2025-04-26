package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Product {

    @Id
    @GeneratedValue
    private Long ID;

    private UUID uuid;

    @ManyToOne
    private Goods goods;

    private LocalDate produceDate;

    private String signature;

    @ElementCollection
    @OrderColumn
    private List<Color> antiCounterfeitingColors = new ArrayList<>();

    private Long validationCount = 0L;

    private Boolean isCounterfeit = false;

    public void increaseValidationCount() {
        validationCount++;
    }

    public Product(Goods goods, LocalDate produceDate) {
        this.goods = goods;
        this.produceDate = produceDate;
    }

    public void initUUID() {
        if (uuid != null) {
            throw new IllegalStateException("The product already has a UUID!");
        }
        this.uuid = UUID.randomUUID();
    }

}
