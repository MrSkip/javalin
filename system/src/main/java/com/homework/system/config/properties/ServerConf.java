package com.homework.system.config.properties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Getter;

public class ServerConf {
    public static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        loadProperties();
    }

    private static void loadProperties() {
        Config conf = ConfigFactory.load();

        PROPERTIES.serverPort = conf.getInt("server.port");
        PROPERTIES.javaPath = conf.getString("java.path");
        PROPERTIES.applicationPath = conf.getString("application.path");
    }

    @Getter
    public static class Properties {
        private Properties() {
            synchronized (this) {
                if (PROPERTIES != null) {
                    throw new UnsupportedOperationException(
                            "Could not create already existed instance"
                    );
                }
            }
        }

        private int serverPort;
        private String javaPath;
        private String applicationPath;
    }

}
