package com.apeng.anticounterfeitbackend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ValidationRequest {
    private UUID uuid;
    private String signature;
}
