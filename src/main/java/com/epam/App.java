package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.DijkstraSearch;
import com.epam.impl.Edge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class app demonstrates how your implementation of {@link com.epam.api.GpsNavigator} is intended to be used.
 */
public class App {

    public static void main(String[] args) {
        final GpsNavigator navigator = new StubGpsNavigator();
        navigator.readData("graph.txt");

        final Path path = navigator.findPath("C", "A");
        System.out.println(path);
    }

    private static class StubGpsNavigator implements GpsNavigator {
        private List<Edge> nodes = new ArrayList<>();


        @Override
        public void readData(String filePath) {
            try {
                Scanner sc = new Scanner(new FileInputStream(filePath));
                while (sc.hasNextLine()) {
                    String[] line = sc.nextLine().split("\\s");
                    String firstPoint = line[0];
                    String secondPoint = line[1];
                    double weight = Double.parseDouble(line[2]);
                    int price = Integer.parseInt(line[3]);
                    nodes.add(new Edge(firstPoint, secondPoint, weight, price));
                }
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("File:" + filePath + " doesn't exist");
            }
        }

        @Override
        public Path findPath(String pointA, String pointB) {
            List<Edge> edges = DijkstraSearch.findPath(nodes, pointA, pointB, Edge::getLength);
            List<String> path = new ArrayList<>();
            int cost = 0;
            for (Edge edge : edges) {
                if (!path.contains(edge.edgeFrom())) path.add(edge.edgeFrom());
                if (!path.contains(edge.edgeTo())) path.add(edge.edgeTo());
                cost += edge.getCost();
            }
            return new Path(path, cost);
        }
    }
}
