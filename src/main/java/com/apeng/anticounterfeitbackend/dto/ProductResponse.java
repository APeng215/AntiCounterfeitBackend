package com.apeng.anticounterfeitbackend.dto;

import com.apeng.anticounterfeitbackend.entity.Goods;
import com.apeng.anticounterfeitbackend.entity.Product;
import com.apeng.anticounterfeitbackend.entity.QueryInfo;
import com.apeng.anticounterfeitbackend.util.ColorUtils;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ProductResponse {

    private Long ID;
    private UUID uuid;
    private Goods goods;
    private LocalDate produceDate;
    private String signature;
    private List<String> acColorsInHex;
    private Long queryCount;
    private Boolean isCounterfeit;
    private List<QueryInfo> queries;

    public ProductResponse(Product product) {
        this.ID = product.getID();
        this.uuid = product.getUuid();
        this.goods = product.getGoods();
        this.produceDate = product.getProduceDate();
        this.signature = product.getSignature();
        this.queryCount = product.getValidationCount();
        this.isCounterfeit = product.getIsCounterfeit();
        this.queries = product.getQueries();
        acColorsInHex = new ArrayList<>(product.getAntiCounterfeitingColors().stream().map(ColorUtils::toHex).toList());
    }

}
