package com.apeng.anticounterfeitbackend.repository;

import com.apeng.anticounterfeitbackend.entity.IPQueryTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPQueryTimeRepository extends JpaRepository<IPQueryTime, String> {
}
