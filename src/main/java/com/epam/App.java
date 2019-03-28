package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.graph.DijkstraSearch;
import com.epam.impl.Edge;
import com.epam.impl.util.FileLinesReader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * This class app demonstrates how your implementation of {@link com.epam.api.GpsNavigator} is intended to be used.
 */
public class App {

    public static void main(String[] args) {
        final GpsNavigator<Edge> navigator = new StubGpsNavigator();
        navigator.readData("graph.txt");

        final Path path = navigator.findPath("A", "C", Edge::getLength);
        System.out.println(path);
    }

    private static class StubGpsNavigator implements GpsNavigator<Edge> {
        private final List<Edge> nodes = new ArrayList<>();

        @Override
        public void readData(String filePath) {
            List<String> input = FileLinesReader.readLines(filePath);
            for (String line : input) {
                String[] data = line.split("\\s");
                String firstPoint = data[0];
                String secondPoint = data[1];
                double weight = Double.parseDouble(data[2]);
                int price = Integer.parseInt(data[3]);
                nodes.add(new Edge(firstPoint, secondPoint, weight, price));
            }
        }

        @Override
        public Path findPath(String pointA, String pointB, Function<? super Edge, Double> costCounter) {
            List<Edge> edges = DijkstraSearch.findPath(nodes, pointA, pointB, costCounter);
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
