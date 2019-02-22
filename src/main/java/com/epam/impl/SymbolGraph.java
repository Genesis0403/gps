package com.epam.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class SymbolGraph {
    private Map<String, Integer> verticesIndexes = new HashMap<>();
    private List<String> vertices;
    private Graph graph;

    public SymbolGraph(String fileName) {
        Path path = InputChecker.checkFileExistence(fileName);
        fillVerticesIndexes(path);
        fillVerticesList();
        buildGraph(path);
    }

    private void fillVerticesIndexes(Path fileName) {
        try {
            Scanner sc = new Scanner(new FileInputStream(fileName.toFile()));
            int count = 0;
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s");
                InputChecker.checkString(line[0], line[1]);
                if (!verticesIndexes.containsKey(line[0])) verticesIndexes.put(line[0], count++);
                if (!verticesIndexes.containsKey(line[1])) verticesIndexes.put(line[1], count++);
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File:" + fileName + " doesn't exist");
        }
    }

    private void fillVerticesList() {
        vertices = new ArrayList<>(verticesIndexes.size());
        for (int i = 0; i < verticesIndexes.size(); i++) vertices.add(null);
        for (String key : verticesIndexes.keySet()) {
            vertices.set(verticesIndexes.get(key), key);
        }
    }

    private void buildGraph(Path fileName) {
        try {
            graph = new Graph(verticesIndexes.size());
            Scanner sc = new Scanner(new FileInputStream(fileName.toFile()));
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s");
                int firstPoint = verticesIndexes.get(line[0]);
                int secondPoint = verticesIndexes.get(line[1]);
                double weight = Double.parseDouble(line[2]);
                int price = Integer.parseInt(line[3]);
                graph.addEdge(new Edge(firstPoint, secondPoint, weight, price));
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File:" + fileName + " doesn't exist");
        }
    }

    public boolean contains(String vertex) {
        if (vertex == null) throw new IllegalStateException("Vertex can't be null.");
        return verticesIndexes.containsKey(vertex);
    }

    public int index(String vertex) {
        if (vertex == null) throw new IllegalStateException("Vertex can't be null.");
        return verticesIndexes.get(vertex);
    }

    public String vertex(int index) {
        if (index < 0 || index >= vertices.size()) throw new IllegalArgumentException();
        return vertices.get(index);
    }

    public Graph getGraph() {
        return graph;
    }
}
