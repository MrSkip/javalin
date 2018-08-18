package com.homework.system.controller.base;

import io.javalin.Context;
import io.javalin.Javalin;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractController {
    protected final Javalin app;
    private static final Set<AbstractController> CONTROLLERS;

    static {
        CONTROLLERS = new HashSet<>();
    }

    {
        CONTROLLERS.add(this);
    }

    public AbstractController(Javalin javalin) {
        this.app = javalin;
    }

    public static void initMappings() {
        CONTROLLERS.forEach(AbstractController::urlMapping);
    }

    public abstract void urlMapping();

    protected Integer requiredAttr(Context ctx, String name) {
        String[] attrs = ctx.request().getParameterMap().get(name);
        if (attrs == null || attrs.length <= 0) {
            throw new RuntimeException("Request param not present");
        }
        return Integer.parseInt(attrs[0]);
    }

}
