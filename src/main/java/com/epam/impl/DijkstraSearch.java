package com.epam.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DijkstraSearch {
    private List<Edge> edgeTo;
    private List<Node> distTo;
    private PriorityQueue<Node> pq;

    public DijkstraSearch(Graph g, int s) {
        edgeTo = new ArrayList<>(g.getVerticesAmount());
        distTo = new ArrayList<>(g.getVerticesAmount());
        pq = new PriorityQueue<>(g.getVerticesAmount());
        fillLists(g.getVerticesAmount(), s);
        pq.insert(s, distTo.get(s));
        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }

    private class Node implements Comparable<Node> {

        private double weight;
        private int coast;

        public Node(double weight, int coast) {
            this.weight = weight;
            this.coast = coast;
        }

        @Override
        public int compareTo(Node that) {
            int result = Double.compare(weight, that.weight);
            return result == 0 ? Integer.compare(coast, that.coast): result;
        }
    }

    private void fillLists(int size, int s) {
        for (int i = 0; i < size; i++) {
            edgeTo.add(null);
            distTo.add(new Node(Double.POSITIVE_INFINITY, 0));
        }
        distTo.set(s, new Node(0.0, 0));
    }

    public void relax(Graph g, int v) {
        for (Edge e : g.adj(v)) {
            int w = e.to();
            if (distTo(w) > distTo(v) + e.getWeight()) {
                distTo.set(w, new Node(distTo(v) + e.getWeight(), distTo.get(v).coast + e.getCost()));
                edgeTo.set(w, e);
                if (pq.contains(w)) pq.change(w, distTo.get(w));
                else pq.insert(w, distTo.get(w));
            } else if (distTo(w) == distTo(v) + e.getWeight()) {
                boolean cmp = distTo.get(w).coast > distTo.get(v).coast + e.getCost();
                if (cmp) {
                    distTo.set(w, new Node(distTo(w), distTo.get(v).coast + e.getCost()));
                    edgeTo.set(w, e);
                    pq.change(w, distTo.get(w));
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo.get(v).weight;
    }

    public boolean hastPathTo(int v) {
        return distTo.get(v).weight < Double.POSITIVE_INFINITY;
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
