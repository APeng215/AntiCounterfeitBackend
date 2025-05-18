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
        return acColorService.add(Color.decode(colorInHex));
    }

    @GetMapping
    public List<Color> get() {
        return acColorService.getAll();
    }

    @DeleteMapping()
    public boolean delete(@RequestBody String colorInHex) {
        System.out.println(colorInHex);
        System.out.println(Color.decode(colorInHex));
        return acColorService.delete(Color.decode(colorInHex));
    }

}
