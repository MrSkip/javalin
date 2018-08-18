package com.homework.system.config.javalin;

import com.google.inject.AbstractModule;
import com.homework.system.controller.ControllerModule;
import com.homework.system.service.ServiceModule;

public class JavalinAppModule extends AbstractModule {

    @Override
    protected void configure() {
        install(JavalinWebModule.create());

        install(new ServiceModule());
        install(new ControllerModule());
    }
}
