package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.Config;
import com.apeng.anticounterfeitbackend.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ConfigServiceImpl implements ConfigService{

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public Long getQueryCoolDownInMinutes() {
        if (configRepository.existsById(Config.ConfigKey.QUERY_COOLDOWN)) {
            return configRepository.findById(Config.ConfigKey.QUERY_COOLDOWN)
                    .map(config -> Long.parseLong(config.getValue()))
                    .orElseThrow();
        }
        return 0L;
    }

    @Override
    public Long setQueryCoolDownInMinutes(Long duration) {
        Config newConfig = configRepository.save(new Config(Config.ConfigKey.QUERY_COOLDOWN, duration.toString()));
        return Long.parseLong(newConfig.getValue());
    }

    @Override
    public Duration getQueryCoolDown() {
        return Duration.ofMinutes(getQueryCoolDownInMinutes());
    }


}
