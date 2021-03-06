package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.DijkstraSearch;
import com.epam.impl.Edge;
import com.epam.impl.SymbolGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class app demonstrates how your implementation of {@link com.epam.api.GpsNavigator} is intended to be used.
 */
public class App {

    public static void main(String[] args) {
        final GpsNavigator navigator = new StubGpsNavigator();
        navigator.readData("D:\\Gps\\road_map.ext");

        final Path path = navigator.findPath("C", "A");
        System.out.println(path);
    }

    private static class StubGpsNavigator implements GpsNavigator {
        private SymbolGraph symbolGraph;

        @Override
        public void readData(String filePath) {
            symbolGraph = new SymbolGraph(filePath);
        }

        @Override
        public Path findPath(String pointA, String pointB) {
            Path path = new Path(Collections.singletonList("none"), 0);
            try {
                DijkstraSearch search = new DijkstraSearch(symbolGraph.getGraph(), symbolGraph.index(pointA));
                List<Edge> edges = search.pathTo(symbolGraph.index(pointB));
                List<String> vertices = new ArrayList<>(edges.size());
                int cost = 0;
                for (Edge e : edges) {
                    if (!vertices.contains(symbolGraph.vertex(e.from()))) vertices.add(symbolGraph.vertex(e.from()));
                    if (!vertices.contains(symbolGraph.vertex(e.to()))) vertices.add(symbolGraph.vertex(e.to()));
                    cost += e.getCost();
                }
                path = new Path(vertices, cost);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
            return path;
        }
    }
}
