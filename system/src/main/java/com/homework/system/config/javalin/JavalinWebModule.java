package com.homework.system.config.javalin;

import com.google.inject.AbstractModule;
import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;

public class JavalinWebModule extends AbstractModule {
    private Javalin javalin;

    public JavalinWebModule(Javalin javalin) {
        this.javalin = javalin;
    }

    @NotNull
    public static JavalinWebModule create() {
        return new JavalinWebModule(Javalin.create());
    }

    @Override
    protected void configure() {
        bind(Javalin.class).toInstance(javalin);
    }
}
