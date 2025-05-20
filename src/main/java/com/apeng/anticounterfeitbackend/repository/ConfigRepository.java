package com.apeng.anticounterfeitbackend.repository;

import com.apeng.anticounterfeitbackend.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<Config, Config.ConfigKey> {
}
