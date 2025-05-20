package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class IPQueryTime {

    @Id
    private String ip;
    private LocalDateTime latestQueryTime = LocalDateTime.now();

    public LocalDateTime updateTime2Now() {
        latestQueryTime = LocalDateTime.now();
        return latestQueryTime;
    }

}
