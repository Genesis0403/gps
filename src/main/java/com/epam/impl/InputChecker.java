package com.epam.impl;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputChecker {

    private InputChecker() {
    }

    public static void checkString(String... strings) {
        for (String s : strings) {
            if (!s.matches("[a-zA-Z]+")) throw new IllegalStateException("Vertices must be string.");
        }
    }

    public static Path checkFileExistence(String name) {
        Path path = Paths.get(name);
        if (!Files.exists(path)) throw new IllegalArgumentException("File \"" + name + "\" doesn't exist");
        return path;
    }
}