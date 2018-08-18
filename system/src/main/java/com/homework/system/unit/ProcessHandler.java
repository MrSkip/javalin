package com.homework.system.unit;

import com.homework.system.config.common.StreamGobbler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;

@Slf4j
public class ProcessHandler {

    private ProcessHandler() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        Process p =
//        ProcessHandle.allProcesses()
//                .filter(ph -> ph.info().command().isPresent())
//                .forEach(ProcessHandler::dumpProcessInfo);
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("ls");
        builder.directory(new File(System.getProperty("user.home")));
        Process process = builder.start();
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exitCode = process.waitFor();
        assert exitCode == 0;
    }

    public static boolean execute(Map<String, String> commands) {
        commands.forEach((key, value) -> {
            log.info("Executing command {} at folder {}", key, value);
            if (execute(key, value)) {
                log.debug("Executed successfully");
            } else {
                log.info("Failed to execute");
            }
        });
        return true;
    }

    public static boolean execute(String command, String directory) {
        ProcessBuilder builder = new ProcessBuilder()
                .command(command.split(","))
                .directory(new File(directory));

        try {
            Process process = builder.start();

            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), log::info);

            Executors.newSingleThreadExecutor().submit(streamGobbler);

            return process.waitFor() != 0;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    private static void dumpProcessInfo(ProcessHandle ph) {
        System.out.println("PROCESS INFORMATION");
        System.out.println("===================");
        System.out.printf("Process id: %d%n", ph.pid());
        ProcessHandle.Info info = ph.info();
        System.out.printf("Command: %s%n", info.command().orElse(""));
        String[] args = info.arguments().orElse(new String[]{});
        System.out.println("Arguments:");
        for (String arg : args)
            System.out.printf("   %s%n", arg);
        System.out.printf("Command line: %s%n", info.commandLine().orElse(""));
        System.out.printf("Start time: %s%n",
                info.startInstant().orElse(Instant.now()).toString());
        System.out.printf("Run time duration: %sms%n",
                info.totalCpuDuration()
                        .orElse(Duration.ofMillis(0)).toMillis());
        System.out.printf("Owner: %s%n", info.user().orElse(""));
        System.out.println();
    }

//    public static boolean executeCommand(String command) {
//        Objects.requireNonNull(command);
////        Pro
//    }

    public static boolean checkAliveOfProcess(int pid) {
        Optional<ProcessHandle> process = ProcessHandle.of(pid);
        if (process.isPresent()) {
            log.info("The process {} is alive\n", pid);
        } else {
            log.info("The process {} is not active\n", pid);
        }
        return process.isPresent();
    }

}
