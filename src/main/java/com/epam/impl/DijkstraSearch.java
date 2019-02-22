package com.epam.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DijkstraSearch {
    private List<Edge> edgeTo;
    private List<Double> distTo;
    private PriorityQueue<Double> pq;

    public DijkstraSearch(Graph g, int s) {
        edgeTo = new ArrayList<>(g.getVerticesAmount());
        distTo = new ArrayList<>(g.getVerticesAmount());
        pq = new PriorityQueue<>(g.getVerticesAmount());
        fillLists(g.getVerticesAmount(), s);
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }

    private void fillLists(int size, int s) {
        for (int i = 0; i < size; i++) {
            edgeTo.add(null);
            distTo.add(Double.POSITIVE_INFINITY);
        }
        distTo.set(s, 0.0);
    }

    public void relax(Graph g, int v) {
        for (Edge e : g.adj(v)) {
            int w = e.to();
            if (distTo.get(w) > distTo.get(v) + e.getWeight()) {
                distTo.set(w, distTo(v) + e.getWeight());
                edgeTo.set(w, e);
                if (pq.contains(w)) pq.change(w, distTo.get(w));
                else pq.insert(w, distTo.get(w));
            }
        }
    }

    public double distTo(int v) {
        return distTo.get(v);
    }

    public boolean hastPathTo(int v) {
        return distTo.get(v) < Double.POSITIVE_INFINITY;
    }

    public List<Edge> pathTo(int v) {
        if (!hastPathTo(v)) throw new IllegalStateException();
        List<Edge> path = new ArrayList<>();
        for (Edge e = edgeTo.get(v); e != null; e = edgeTo.get(e.from())) {
            path.add(e);
        }
        Collections.reverse(path);
        return path;
    }
}
