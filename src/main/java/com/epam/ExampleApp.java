package com.epam;

import com.epam.api.GpsNavigator;
import com.epam.api.Path;
import com.epam.impl.DijkstraSearch;
import com.epam.impl.Edge;
import com.epam.impl.SymbolGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * This class app demonstrates how your implementation of {@link com.epam.api.GpsNavigator} is intended to be used.
 */
public class ExampleApp {

    public static void main(String[] args) {
        final GpsNavigator navigator = new StubGpsNavigator();
        navigator.readData("graph.txt");//D:\Gps\road_map.ext

        final Path path = navigator.findPath("A", "E");
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
            DijkstraSearch search = new DijkstraSearch(symbolGraph.getGraph(), symbolGraph.index(pointA));
            List<Edge> edges = search.pathTo(symbolGraph.index(pointB));
            List<String> path = new ArrayList<>(edges.size());
            int cost = 0;
            for (Edge e : edges) {
                if (!path.contains(symbolGraph.vertex(e.from()))) path.add(symbolGraph.vertex(e.from()));
                if (!path.contains(symbolGraph.vertex(e.to()))) path.add(symbolGraph.vertex(e.to()));
                cost += e.getCost();
            }
            return new Path(path, cost);
        }
    }
}
