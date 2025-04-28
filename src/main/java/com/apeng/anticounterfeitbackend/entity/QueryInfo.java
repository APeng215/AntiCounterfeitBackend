package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.sql.Timestamp;

@Embeddable
@Data
public class QueryInfo {
    private Location location;
    private Timestamp queryTime;
}
