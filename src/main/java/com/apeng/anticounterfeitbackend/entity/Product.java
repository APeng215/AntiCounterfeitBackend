package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
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

    private Date produceDate;

    private String signature;

    @ElementCollection
    @OrderColumn
    private List<Color> antiCounterfeitingColors = new ArrayList<>();

    public Product(Goods goods, Date produceDate) {
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
