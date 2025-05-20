package com.apeng.anticounterfeitbackend.service;

public interface IPQueryTimeService {
    boolean isIPinCd(String ip);
    void updateQueryTime(String ip);
}
