package heaps;

import java.util.Comparator;
import java.util.Iterator;

public class MaxPQ<T> implements Iterable<T> {
    private T[] pq;
    private Comparator<T> comparator;
    private int N;

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int capacity) {
        pq = (T[]) new Object[capacity + 1];
        N = 0;
    }

    public MaxPQ(Comparator<T> comparator) {
        this(1, comparator);
    }

    public MaxPQ(int capacity, Comparator<T> comparator) {
        this.comparator = comparator;
        pq = (T[]) new Object[capacity + 1];
        N = 0;
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

    /*
     * Insert a new element into the heap
     * 1. If the array is full, resize the array
     * 2. Add the new element at the end of the array
     * 3. Move the new element up to its correct position
     */
    public void insert(T x) {
        if (N == pq.length - 1)
            resize(2 * pq.length);
        pq[++N] = x;
        swim(N);
    }

    /*
     * Bubble up the element at index k to its correct position
     * 1. if the child node(k) is greater than its parent node(k/2)
     * 2. Exchange the child node with the parent node
     * 3. Move up to the parent node until the heap order is restored
     */
    void swim(int k) {
        while (k > 1 && less(k / 2, k)) { // parent of k is k/2
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /*
     * Delete the maximum element from the heap
     * 1. Exchange the maximum element with the last element in the heap
     * 2. Move down (sink the new element at index 1 down) to its correct position
     * 3. Resize the array if the number of elements is less than or equal to 1/4 of
     * the array size
     * 
     */
    public T delMax() {
        if (N == 0)
            throw new RuntimeException("Priority queue underflow");
        T max = pq[1];
        exch(1, N--); // exchange the maximum element with the last element in the heap
        sink(1); // sink the new element at index 1 down to its correct position
        pq[N + 1] = null; // to avoid loitering
        if ((N > 0) && (N == (pq.length - 1) / 4))
            resize(pq.length / 2);
        return max;
    }

    /*
     * Bubble down the element at index k to its correct position
     * 1. If the node at index k(i.e. parent node) has at least one child, j = 2 * k
     * 2. If the right child of the (parent)node at index k is less than the left
     * child, move to the right child
     * 3. If the node at index k(parent node) is not less than the node at index
     * j(child), break
     * 4. Exchange the node at index k with the node at index j
     * 5. Move down to the child node
     */
    void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j)) // parent node is bigger so no need to sink
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
            return copy.delMax();
        }
    }

}

/*
 * Time Complexity:
 * Heap construction : O(n)
 * Insertion of an element : O(log n)
 * Deletion of the maximum element : O(log n)
 * Get the maximum element : O(1)
 * 
 * Space Complexity: O(n)
 * 
 */
