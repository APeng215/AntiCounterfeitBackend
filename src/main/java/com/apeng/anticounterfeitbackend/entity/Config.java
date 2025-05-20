package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Config {

    @Id
    @Enumerated(EnumType.STRING)
    private ConfigKey configKey;
    private String value;

    public enum ConfigKey {
        QUERY_COOLDOWN // long in minutes
    }

}
