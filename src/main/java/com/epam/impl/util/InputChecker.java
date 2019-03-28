package com.epam.impl.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputChecker {

    private InputChecker() {
    }

    public static <T> void checkForNull(T obj) {
        if (obj == null) throw new IllegalArgumentException("Null is not allowed.");
    }

    public static void checkString(String... strings) {
        for (String s : strings) {
            if (!s.matches("[a-zA-Z]+")) throw new IllegalStateException("Vertices must be string.");
        }
    }

    public static Path checkFile(String name) {
        Path path = Paths.get(name);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File \"" + name + "\" doesn't exist.");
        }
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("File \"" + name + "\" is not readable.");
        }
        return path;
    }
}