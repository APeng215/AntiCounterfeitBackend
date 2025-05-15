package com.apeng.anticounterfeitbackend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductRequest {
    private String goodsName;
    private long produceNum;
    private LocalDate produceDate;

    public boolean hasNullField() {
        return goodsName == null || produceNum == 0 || produceDate == null;
    }

}