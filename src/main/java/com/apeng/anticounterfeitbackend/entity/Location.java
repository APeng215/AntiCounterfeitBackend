package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Embeddable
@Data
public class Location {
    private String nation, province, city, district;
    private Double lat;
    private Double lng;
}
