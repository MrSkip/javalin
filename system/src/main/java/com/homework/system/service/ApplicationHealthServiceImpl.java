package com.homework.system.service;

import com.homework.system.unit.ProcessHandler;

public class ApplicationHealthServiceImpl implements ApplicationHealthService {

    @Override
    public boolean healthCheck(int pid) {
        return ProcessHandler.checkAliveOfProcess(pid);
    }
}
