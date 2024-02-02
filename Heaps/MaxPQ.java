package Heaps;

import java.util.Comparator;
import java.util.Iterator;

public class MaxPQ<T> implements Iterable<T> {
    private T[] pq;
    private Comparator<T> comparator;
    private int N;

    public MaxPQ(int capacity) {
        pq = (T[]) new Object[capacity + 1];
        N = 0;
    }

    public MaxPQ(int capacity, Comparator<T> comparator) {
        this.comparator = comparator;
        pq = (T[]) new Object[capacity + 1];
        N = 0;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(Comparator<T> comparator) {
        this(1, comparator);
    }

    public MaxPQ(T[] keys) {
        N = keys.length;
        pq = (T[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++) {
            pq[i + 1] = keys[i];
        }
        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }
    }

    public void insert(T x) {
        if (N == pq.length - 1)
            resize(2 * pq.length);
        pq[++N] = x;
        swim(N);
    }

    void swim(int k) {
        while (k > 1 && less(k / 2, k)) { // parent of k is k/2
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public T delMin() {
        if (N == 0)
            throw new RuntimeException("Priority queue underflow");
        T min = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        if ((N > 0) && (N == (pq.length - 1) / 4))
            resize(pq.length / 2);
        return min;
    }

    void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    void exch(int i, int j) {
        T swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public T max() {
        if (isEmpty())
            throw new RuntimeException("Priority queue underflow");
        return pq[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Iterator<T> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<T> {
        private MaxPQ<T> copy;

        public HeapIterator() {
            if (comparator == null)
                copy = new MaxPQ<T>(size());
            else
                copy = new MaxPQ<T>(size(), comparator);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public T next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return copy.delMin();
        }
    }

}
