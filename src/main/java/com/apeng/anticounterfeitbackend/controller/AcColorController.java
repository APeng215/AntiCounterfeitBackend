package com.apeng.anticounterfeitbackend.controller;

import com.apeng.anticounterfeitbackend.service.AcColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;


@RestController
@RequestMapping("/colors")
public class AcColorController {

    @Autowired
    AcColorService acColorService;

    @PostMapping
    public Color add(@RequestBody String colorInHex) {
        return acColorService.add(Color.getColor(colorInHex));
    }

    @GetMapping
    public List<Color> get() {
        return acColorService.getAll();
    }

    @DeleteMapping("/{colorInHex}")
    public boolean delete(@PathVariable String colorInHex) {
        return acColorService.delete(Color.getColor(colorInHex));
    }

}
