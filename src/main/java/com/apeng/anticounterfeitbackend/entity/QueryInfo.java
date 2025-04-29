package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.sql.Timestamp;

@Embeddable
@Data
public class QueryInfo {
    private String ip;
    private Location location;
    private Timestamp queryTime;
}
