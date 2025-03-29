package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Goods {
    @Id
    private String name;
    private String description;
}
