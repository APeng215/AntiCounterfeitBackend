package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.AntiCounterfeitingColor;
import com.apeng.anticounterfeitbackend.repository.AntiCounterfeitingColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class AntiCounterfeitingColorServiceImpl implements  AntiCounterfeitingColorService {

    @Autowired
    AntiCounterfeitingColorRepository repository;

    @Override
    public AntiCounterfeitingColor add(AntiCounterfeitingColor antiCounterfeitingColor) {
        return repository.save(antiCounterfeitingColor);
    }

    @Override
    public void deleteById(Color color) {
        repository.deleteById(color);
    }

    @Override
    public List<AntiCounterfeitingColor> getAll() {
        return repository.findAll();
    }
}
