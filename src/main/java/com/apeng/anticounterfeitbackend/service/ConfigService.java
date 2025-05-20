package com.apeng.anticounterfeitbackend.service;

import java.time.Duration;

public interface ConfigService {
    Long getQueryCoolDownInMinutes();
    Long setQueryCoolDownInMinutes(Long duration);
    Duration getQueryCoolDown();
}
