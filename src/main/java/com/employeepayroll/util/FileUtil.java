package com.employeepayroll.util;

import java.nio.file.*;
import java.time.LocalDateTime;

public final class FileUtil {
    private FileUtil() {
    }

    public static void log(String message) {
        try {
            Files.writeString(Path.of("application.log"),
                    LocalDateTime.now() + " | " + message + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.err.println("Could not write application log.");
        }
    }
}
