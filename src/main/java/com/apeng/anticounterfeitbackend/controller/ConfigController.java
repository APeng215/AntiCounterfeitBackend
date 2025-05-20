package com.apeng.anticounterfeitbackend.controller;

import com.apeng.anticounterfeitbackend.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/cd")
    public Long getQueryCdInMinutes() {
        return configService.getQueryCoolDownInMinutes();
    }

    @PutMapping("/cd")
    public void setQueryCdInMinutes(@RequestBody Long cd) {
        configService.setQueryCoolDownInMinutes(cd);
    }

}
