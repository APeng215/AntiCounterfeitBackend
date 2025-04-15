package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.AcColor;
import com.apeng.anticounterfeitbackend.repository.AcColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcColorServiceImpl implements AcColorService {

    @Autowired
    AcColorRepository repository;

    @Override
    public Color add(Color color) {
        return repository.save(new AcColor(color)).getColor();
    }

    @Override
    public boolean delete(Color color) {
        if (repository.existsById(color)) {
            repository.deleteById(color);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Color> getAll() {
        return repository.findAll().stream().map(AcColor::getColor).toList();
    }
}
