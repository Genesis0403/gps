package com.epam.graph;

import java.util.Objects;

public class Edge {
    private int fromEdge;
    private int toEdge;
    private double weight;
    private int cost;

    public Edge(int fromEdge, int toEdge, double weight, int cost) {
        this.fromEdge = fromEdge;
        this.toEdge = toEdge;
        this.weight = weight;
        this.cost = cost;
    }

    public int from() {
        return fromEdge;
    }

    public int to() {
        return toEdge;
    }

    public double getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return fromEdge == edge.fromEdge &&
                toEdge == edge.toEdge &&
                Double.compare(edge.weight, weight) == 0 &&
                cost == edge.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromEdge, toEdge, weight, cost);
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", fromEdge, toEdge, weight);
    }
}
