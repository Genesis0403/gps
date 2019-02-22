package com.epam.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    private int verticesAmount;
    private int slatsAmount;
    private List<List<Edge>> adj;

    public Graph(int verticesAmount) {
        this.verticesAmount = verticesAmount;
        slatsAmount = 0;
        adj = new ArrayList<>(verticesAmount);
        Collections.fill(adj, new ArrayList<>());
    }

    public void addEdge(Edge e) {
        if (e == null) throw new IllegalStateException("Edge must'n be null.");
        adj.get(e.from()).add(e);
        slatsAmount++;
    }

    public int getVerticesAmount() {
        return verticesAmount;
    }

    public int getSlatsAmount() {
        return slatsAmount;
    }

    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<>();
        for (List<Edge> anAdj : adj) {
            edges.addAll(anAdj);
        }
        return edges;
    }
}
