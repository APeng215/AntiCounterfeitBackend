package com.apeng.anticounterfeitbackend.repository;

import com.apeng.anticounterfeitbackend.entity.AntiCounterfeitingColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;


public interface AntiCounterfeitingColorRepository extends JpaRepository<AntiCounterfeitingColor, Color> {
}
