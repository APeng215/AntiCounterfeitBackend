package com.apeng.anticounterfeitbackend.dto;

import com.apeng.anticounterfeitbackend.entity.Goods;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ProductRequest {
    private final String goodsName;
    private final long produceNum;
    private final LocalDate produceDate;

    public boolean hasNullField() {
        return goodsName == null || produceNum == 0 || produceDate == null;
    }

}