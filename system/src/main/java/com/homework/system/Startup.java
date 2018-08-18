package com.homework.system;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.homework.system.config.javalin.JavalinAppModule;
import com.homework.system.controller.ApplicationHealthController;
import io.javalin.Javalin;

import static com.homework.system.config.properties.ServerConf.PROPERTIES;

public class Startup {

    public static void main(String[] args) {
        var injector = Guice.createInjector(new JavalinAppModule());
        injector.getInstance(Startup.class);
    }

    @Inject
    private void boot(Javalin javalin) {
        javalin.port(PROPERTIES.getServerPort());
        javalin.start();
        ApplicationHealthController.initMappings();
    }


}
