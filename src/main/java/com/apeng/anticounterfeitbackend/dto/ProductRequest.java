package com.apeng.anticounterfeitbackend.dto;

import com.apeng.anticounterfeitbackend.entity.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class ProductRequest {
    private String goodsName;
    private long produceNum;
    private Date produceDate;
}