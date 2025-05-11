package com.apeng.anticounterfeitbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ProductionStats {

    /**
     *      {
     *          "2025": [11, 25, 30, 40, ...] // 12 months
     *      }
     */

    private Map<Long, List<Long>> annualProductions;

}
