package com.epam.impl;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int verticesAmount;
    private int slatsAmount;
    private List<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int verticesAmount) {
        this.verticesAmount = verticesAmount;
        slatsAmount = 0;
        adj = (List<Edge>[])new List[verticesAmount];
        for (int i = 0; i < verticesAmount; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(Edge e) {
        if (e == null) throw new IllegalStateException("Edge must'n be null.");
        adj[e.from()].add(e);
        slatsAmount++;
    }

    public int getVerticesAmount() {
        return verticesAmount;
    }

    public int getSlatsAmount() {
        return slatsAmount;
    }

    public List<Edge> adj(int v) {
        return adj[v];
    }

    public List<Edge> edges() {
        List<Edge> edges = new ArrayList<>();
        for (List<Edge> anAdj : adj) {
            edges.addAll(anAdj);
        }
        return edges;
    }
}
