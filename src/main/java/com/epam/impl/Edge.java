package com.epam.impl;

public class Edge implements Nodable {
    private final String fromEdge;
    private final String toEdge;
    private final double length;
    private final double cost;

    public Edge(String fromEdge, String toEdge, double length, double cost) {
        this.fromEdge = fromEdge;
        this.toEdge = toEdge;
        this.length = length;
        this.cost = cost;
    }

    @Override
    public String edgeFrom() {
        return fromEdge;
    }

    @Override
    public String edgeTo() {
        return toEdge;
    }

    public double getLength() {
        return length;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", fromEdge, toEdge, length);
    }
}
