package com.apeng.anticounterfeitbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.awt.*;

@Data
@Entity
public class AntiCounterfeitingColor {
    @Id
    private Color color;
}
