package com.apeng.anticounterfeitbackend.repository;

import com.apeng.anticounterfeitbackend.entity.AcColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface AcColorRepository extends JpaRepository<AcColor, Color> {
}
