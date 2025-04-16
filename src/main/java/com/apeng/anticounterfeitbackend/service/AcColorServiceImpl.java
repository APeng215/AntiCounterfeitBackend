package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.AcColor;
import com.apeng.anticounterfeitbackend.repository.AcColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
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

    @Transactional
    @Override
    public void add(Color... colors) {
        for (Color color : colors) {
            repository.save(new AcColor(color));
        }
    }

    @Override
    public List<Color> randomPick(int pickNum) {
        // Get colors from repository and convert to a mutable list
        List<Color> colors = repository.findAll()
                .stream()
                .map(acColor -> acColor.getColor())
                .collect(Collectors.toList()); // Mutable list

        // Shuffle the list (modifies it in-place)
        Collections.shuffle(colors);

        // Return the first 'pickNum' colors (or empty if pickNum <= 0)
        return pickNum <= 0 ? List.of()
                : colors.stream().limit(pickNum).collect(Collectors.toList());

    }
}
