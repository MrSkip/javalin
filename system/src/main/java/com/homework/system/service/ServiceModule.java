package com.homework.system.service;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApplicationHealthService.class).to(ApplicationHealthServiceImpl.class);
        bind(AppDeployService.class).to(AppDeployServiceImpl.class);
    }

}
