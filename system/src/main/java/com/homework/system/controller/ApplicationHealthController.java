package com.homework.system.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.homework.system.controller.base.AbstractController;
import com.homework.system.service.ApplicationHealthService;
import io.javalin.Context;
import io.javalin.Javalin;

import static io.javalin.ApiBuilder.get;
import static io.javalin.ApiBuilder.path;

@Singleton
public class ApplicationHealthController extends AbstractController {
    private final ApplicationHealthService healthService;

    @Inject
    public ApplicationHealthController(Javalin javalin,
                                       ApplicationHealthService healthService) {
        super(javalin);
        this.healthService = healthService;
    }

    @Override
    public void urlMapping() {
        app.routes(() -> {
            path("/", () -> {
                get("", Context::response);

                path("health", () -> {
                    get((ctx) -> {
                        var pid = requiredAttr(ctx, "pid");
                        ctx.json(healthService.healthCheck(pid));
                    });
                });
            });
        });
    }
}
