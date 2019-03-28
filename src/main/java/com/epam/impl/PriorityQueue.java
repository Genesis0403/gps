package com.epam.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<K extends Comparable<K>> {
    private final List<Integer> pq;
    private final List<Integer> qp;
    private final List<K> keys;
    private int elementsAmount;
    private int n;

    public PriorityQueue(int elementsAmount) {
        if (elementsAmount < 0) throw new IllegalArgumentException("Negative size is not allowed.");
        this.elementsAmount = elementsAmount;
        keys = new ArrayList<>(elementsAmount + 1);
        pq = new ArrayList<>(elementsAmount + 1);
        qp = new ArrayList<>(elementsAmount + 1);
        for (int i = 0; i <= elementsAmount; i++) {
            pq.add(0);
            qp.add(-1);
            keys.add(null);
        }
    }

    public int delMin() {
        int min = pq.get(1);
        swap(1, n--);
        sink(1);
        qp.set(min, -1);
        keys.set(min, null);
        pq.set(n + 1, null);
        return min;
    }

    public void change(int i, K key) {
        keys.set(i, key);
        swim(qp.get(i));
        sink(qp.get(i));
    }

    public boolean contains(int i) {
        if (i < 0 || i >= elementsAmount) throw new IllegalArgumentException();
        return qp.get(i) != -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(int i, K key) {
        if (contains(i)) throw new IllegalArgumentException("i=" + i + " already exists.");
        if (key == null) throw new IllegalArgumentException("Null is not allowed.");
        n++;
        qp.set(i, n);
        pq.set(n, i);
        keys.set(i, key);
        swim(n);
    }

    private boolean greater(int i, int j) {
        return keys.get(pq.get(i)).compareTo(keys.get(pq.get(j))) > 0;
    }

    private void swap(int i, int j) {
        Collections.swap(pq, i, j);
        qp.set(pq.get(i), i);
        qp.set(pq.get(j), j);
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

}
