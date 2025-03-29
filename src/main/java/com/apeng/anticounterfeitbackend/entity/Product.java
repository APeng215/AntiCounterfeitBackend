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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class Product {
    @ManyToOne
    private Goods goods;
    @Id
    @GeneratedValue
    private Long ID;
    private Date produceDate;
    @Column(unique = true, length = 13)
    private String antiCounterfeitingCode;
    @ManyToMany
    private List<AntiCounterfeitingColor> antiCounterfeitingColors = new ArrayList<>();

}
