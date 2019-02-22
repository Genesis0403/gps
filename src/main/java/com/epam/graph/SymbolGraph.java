package com.epam.graph;

import com.epam.util.InputChecker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class SymbolGraph {
    private Map<String, Integer> verticesIndex = new HashMap<>();
    private List<String> vertices;
    private Graph graph;

    public SymbolGraph(String fileName) {
        Path path = InputChecker.checkFileExistence(fileName);
        fillVerticesIndexes(path);
        fillVerticesList();
        buildGraph(path);
    }

    private void fillVerticesIndexes(Path fileName) {
        Scanner sc;
        try {
            sc = new Scanner(new FileInputStream(fileName.toFile()));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File:" + fileName + " doesn't exist");
        }
        int count = 0;
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("\\s");
            InputChecker.checkString(line[0], line[1]);
            if (!verticesIndex.containsKey(line[0])) verticesIndex.put(line[0], count++);
            if (!verticesIndex.containsKey(line[1])) verticesIndex.put(line[1], count++);
        }
    }

    private void fillVerticesList() {
        vertices = new ArrayList<>(verticesIndex.size());
        Collections.fill(vertices, null);
        for (String key : verticesIndex.keySet()) {
            vertices.set(verticesIndex.get(key), key);
        }
    }

    private void buildGraph(Path fileName) {
        try {
            Scanner sc = new Scanner(new FileInputStream(fileName.toFile()));
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s");
                int firstPoint = verticesIndex.get(line[0]);
                int secondPoint = verticesIndex.get(line[1]);
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
        return verticesIndex.containsKey(vertex);
    }

    public int index(String vertex) {
        if (vertex == null) throw new IllegalStateException("Vertex can't be null.");
        return verticesIndex.get(vertex);
    }
}
