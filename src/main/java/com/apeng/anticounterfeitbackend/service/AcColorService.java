package com.apeng.anticounterfeitbackend.service;


import java.awt.*;
import java.util.List;

public interface AcColorService {
    Color add(Color color);
    boolean delete(Color color);
    List<Color> getAll();
    void add(Color... colors);
    List<Color> randomPick(int pickNum);
}
