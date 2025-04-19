package com.apeng.anticounterfeitbackend.config;

import com.apeng.anticounterfeitbackend.service.AcColorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class Initializer {

    @Bean
    public CommandLineRunner addAdminUser(AcColorService acColorService) {
        return (arg) -> {
            acColorService.add(Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, Color.MAGENTA, Color.YELLOW);
        };
    }

}
