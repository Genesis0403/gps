package com.epam.impl.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileLinesReader {

    public static List<String> readLines(String filePath) {
        InputChecker.checkFile(filePath);
        List<String> lines = new LinkedList<>();
        try {
            Scanner sc = new Scanner(new FileInputStream(filePath));
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File:" + filePath + " doesn't exist");
        }
        return lines;
    }
}
