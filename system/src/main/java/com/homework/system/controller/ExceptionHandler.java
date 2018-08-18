package com.homework.system.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.homework.system.controller.base.AbstractController;
import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ExceptionHandler extends AbstractController {

    @Inject
    public ExceptionHandler(Javalin javalin) {
        super(javalin);
    }

    @Override
    public void urlMapping() {
        app.exception(Exception.class, (e, ctx) -> {
            log.error("", e);
            ctx
                    .status(500)
                    .result(e.getMessage());
            // handle general exceptions here
            // will not trigger if more specific exception-mapper found

        });
    }
}
