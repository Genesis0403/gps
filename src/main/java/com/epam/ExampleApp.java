package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;

import java.util.Arrays;

/**
 * This class app demonstrates how your implementation of {@link com.epam.api.GpsNavigator} is intended to be used.
 */
public class ExampleApp {

    public static void main(String[] args) {
        final GpsNavigator navigator = new StubGpsNavigator();
        navigator.readData("D:\\Gps\\road_map.ext");

        final Path path = navigator.findPath("A", "B");
        System.out.println(path);
    }

    private static class StubGpsNavigator implements GpsNavigator {

        @Override
        public void readData(String filePath) {
            // Read data from file.
        }

        @Override
        public Path findPath(String pointA, String pointB) {
            return new Path(Arrays.asList("A", "C", "D", "E"), 37);
        }
    }
}
