package com.apeng.anticounterfeitbackend.service;

import com.apeng.anticounterfeitbackend.entity.IPQueryTime;
import com.apeng.anticounterfeitbackend.repository.IPQueryTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class IPQueryTimeServiceImpl implements IPQueryTimeService{

    @Autowired
    private IPQueryTimeRepository ipQueryTimeRepository;

    @Autowired
    private ConfigService configService;

    @Override
    public boolean isIPinCd(String ip) {
        if (ipNotExist(ip)) {
            return false;
        }
        Duration cd = configService.getQueryCoolDown();
        LocalDateTime updateTime = ipQueryTimeRepository.findById(ip).orElseThrow().getLatestQueryTime();;
        Duration timeLapse = Duration.between(updateTime, LocalDateTime.now());
        return cd.compareTo(timeLapse) > 0;
    }

    private boolean ipNotExist(String ip) {
        return !ipQueryTimeRepository.existsById(ip);
    }

    @Override
    public void updateQueryTime(String ip) {
        ipQueryTimeRepository.save(new IPQueryTime(ip, LocalDateTime.now()));
    }
}
