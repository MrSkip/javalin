package com.homework.system.controller;

import com.google.inject.AbstractModule;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ApplicationHealthController.class).asEagerSingleton();
        bind(ExceptionHandler.class).asEagerSingleton();
        bind(AppDeployController.class).asEagerSingleton();
    }
}
