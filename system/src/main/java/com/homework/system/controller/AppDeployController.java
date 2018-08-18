package com.homework.system.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.homework.system.controller.base.AbstractController;
import com.homework.system.service.AppDeployService;
import io.javalin.Context;
import io.javalin.Javalin;

import static io.javalin.ApiBuilder.get;
import static io.javalin.ApiBuilder.path;

@Singleton
public class AppDeployController extends AbstractController {
    private final AppDeployService deployService;

    @Inject
    public AppDeployController(Javalin javalin,
                               AppDeployService deployService) {
        super(javalin);
        this.deployService = deployService;
    }

    @Override
    public void urlMapping() {
        app.routes(() -> {
            path("/deploy", () -> {
                get("", Context::response);
                path("sombra-space", () -> {
                    get((ctx) -> {
                        ctx.json(deployService.deployApplication());
                    });
                });
            });
        });
    }
}
