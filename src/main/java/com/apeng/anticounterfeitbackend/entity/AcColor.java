package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.awt.*;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class AcColor {

    @Id
    private Color color;

}
