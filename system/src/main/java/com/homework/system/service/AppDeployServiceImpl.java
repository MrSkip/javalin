package com.homework.system.service;

import com.google.inject.Singleton;
import com.homework.system.config.properties.ServerConf;
import com.homework.system.unit.ProcessHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Singleton
@Slf4j
public class AppDeployServiceImpl implements AppDeployService {

    @Override
    public boolean deployApplication() {
        log.info("Deploying the application");
        var deploy = ServerConf.PROPERTIES.getJavaPath()
                + "/bin/java,-jar,target/sombraspace-0.0.1-SNAPSHOT.jar";
        Map<String, String> commands = Map.of(
                "git,checkout,test-branch", ServerConf.PROPERTIES.getApplicationPath(),
                "mvn,clean,install ", ServerConf.PROPERTIES.getApplicationPath(),
                deploy, ServerConf.PROPERTIES.getApplicationPath()
        );
        ProcessHandler.execute(Map.of("git,checkout,test-branch", ServerConf.PROPERTIES.getApplicationPath()));
        ProcessHandler.execute(Map.of("mvn,clean,install", ServerConf.PROPERTIES.getApplicationPath()));
        ProcessHandler.execute(Map.of(deploy, ServerConf.PROPERTIES.getApplicationPath()));
        return true;
    }

}
