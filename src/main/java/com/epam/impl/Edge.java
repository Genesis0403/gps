package com.epam.impl;

public class Edge implements Nodable {
    private String fromEdge;
    private String toEdge;
    private double length;
    private int cost;

    public Edge(String fromEdge, String toEdge, double length, int cost) {
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

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", fromEdge, toEdge, length);
    }
}
