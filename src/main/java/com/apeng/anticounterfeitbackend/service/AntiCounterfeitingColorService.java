package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.AntiCounterfeitingColor;

import java.awt.*;
import java.util.List;

public interface AntiCounterfeitingColorService {
    AntiCounterfeitingColor add(AntiCounterfeitingColor AntiCounterfeitingColor);
    void deleteById(Color color);
    List<AntiCounterfeitingColor> getAll();
}
