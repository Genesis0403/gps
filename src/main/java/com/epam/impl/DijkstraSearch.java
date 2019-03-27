package com.epam.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class DijkstraSearch {

    public static <T extends Nodable> List<T> findPath(List<T> edges,
                                                       String from,
                                                       String to,
                                                       Function<? super T, Double> costCounter) {
        Graph<T> graph = new Graph<>(edges);
        int verticesAmount = graph.getVerticesAmount();
        List<T> edgeTo = new ArrayList<>(verticesAmount);
        List<Double> distTo = new ArrayList<>(verticesAmount);

        int fromIndex = graph.index(from);
        fillList(edgeTo, null, verticesAmount);
        fillList(distTo, Double.POSITIVE_INFINITY, verticesAmount);
        distTo.set(fromIndex, 0.0);

        PriorityQueue<Double> pq = new PriorityQueue<>(graph.getVerticesAmount());
        pq.insert(fromIndex, distTo.get(fromIndex));
        while (!pq.isEmpty()) {
            relax(graph, pq.delMin(), distTo, edgeTo, pq, costCounter);
        }
        return pathTo(graph, distTo, edgeTo, graph.index(to));
    }

    private static <T> void fillList(List<T> list, T el, int size) {
        for (int i = 0; i < size; i++) {
            list.add(el);
        }
    }

    private static <T extends Nodable> void relax(Graph<T> graph,
                                                  int v,
                                                  List<Double> distTo,
                                                  List<T> edgeTo,
                                                  PriorityQueue<Double> pq,
                                                  Function<? super T, Double> costCounter) {
        for (T e : graph.adj(v)) {
            int w = graph.index(e.edgeTo());
            double cost = costCounter.apply(e);
            if (distTo.get(w) > cost + distTo.get(v)) {
                distTo.set(w, cost + distTo.get(v));
                edgeTo.set(w, e);
                if (pq.contains(w)) pq.change(w, distTo.get(w));
                else pq.insert(w, distTo.get(w));
            }
        }
    }

    private static boolean hasPathTo(List<Double> distTo, int v) {
        return distTo.get(v) < Double.POSITIVE_INFINITY;
    }

    private static <T extends Nodable> List<T> pathTo(Graph<T> graph,
                                                      List<Double> distTo,
                                                      List<T> edgeTo,
                                                      int v) {
        if (!hasPathTo(distTo, v)) return Collections.EMPTY_LIST;
        List<T> path = new ArrayList<>();
        for (T e = edgeTo.get(v); e != null; e = edgeTo.get(graph.index(e.edgeFrom()))) {
            path.add(e);
        }
        Collections.reverse(path);
        return path;
    }
}
