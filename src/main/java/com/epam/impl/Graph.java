package com.epam.impl;

import java.util.*;

public class Graph<T extends Nodable> {
    private final Map<String, Integer> indexes = new HashMap<>();
    private final List<T>[] adj;
    private int verticesAmount;
    private int slatsAmount;

    @SuppressWarnings("unchecked")
    public Graph(List<T> edges) {
        if (edges == null) throw new IllegalStateException();
        this.verticesAmount = countVerticesAmount(edges);
        adj = (List<T>[]) new List[verticesAmount];
        for (int i = 0; i < verticesAmount; i++) {
            adj[i] = new ArrayList<>();
        }
        countVerticesIndexes(edges);
    }

    private void countVerticesIndexes(List<T> edges) {
        int count = 0;
        for (T edge : edges) {
            if (edge == null) throw new IllegalStateException();
            if (!indexes.containsKey(edge.edgeFrom())) indexes.put(edge.edgeFrom(), count++);
            if (!indexes.containsKey(edge.edgeTo())) indexes.put(edge.edgeTo(), count++);
            addEdge(edge);
        }
    }

    private int countVerticesAmount(List<T> edges) {
        Set<String> eachVertex = new HashSet<>();
        for (T edge : edges) {
            eachVertex.add(edge.edgeFrom());
            eachVertex.add(edge.edgeTo());
        }
        return eachVertex.size();
    }

    public void addEdge(T e) {
        if (e == null) throw new IllegalStateException("Edge must'n be null.");
        adj[indexes.get(e.edgeFrom())].add(e);
        slatsAmount++;
    }

    public int index(String vertex) {
        if (vertex == null || !indexes.keySet().contains(vertex)) throw new IllegalStateException();
        return indexes.get(vertex);
    }

    public int getVerticesAmount() {
        return verticesAmount;
    }

    public List<T> adj(int v) {
        return adj[v];
    }

    public List<T> edges() {
        List<T> edges = new ArrayList<>();
        for (List<T> anAdj : adj) {
            edges.addAll(anAdj);
        }
        return edges;
    }
}
