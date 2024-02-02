package Heaps;

public class MinPQ<T> implements Iterable<T> {
    /*
     * MinPQ is a priority queue that returns the minimum element in the queue.
     * It is implemented using a binary heap.
     * 
     * The binary heap is a complete binary tree, which is a tree that is completely
     * filled,
     * with the possible exception of the bottom level, which is filled from left to
     * right.
     * 
     * The binary heap is represented as an array, where the root is at index 1.
     * The parent of the node at index k is at index k/2.
     * The children of the node at index k are at indices 2k and 2k+1.
     * 
     * The binary heap is ordered such that the key in the parent node is less than
     * or equal to the keys in the children nodes.
     * 
     * Time complexity:
     * - Insertion: O(log n)
     * - Deletion: O(log n)
     * - Find minimum: O(1)
     */

    private T[] pq;
    private int n;

    public MinPQ(int capacity) {
        pq = (T[]) new Object[capacity + 1];
        n = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(T[] keys) {
        n = keys.length;
        pq = (T[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }

        // Heapify: build the heap in bottom-up manner by calling sink() for each node
        // in reverse level order [Time complexity: O(n)]
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
    }

    // insert a new element in the end of the array and swim it up to its correct
    // position
    public void insert(T x) {
        if (n == pq.length - 1)
            resize(2 * pq.length);
        pq[++n] = x;
        swim(n); // swim the new element up to its correct position i.e. bubble up
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) { // while the parent of the node at index k is greater than the node at
                                             // index k, exchange them
            exch(k, k / 2);
            k = k / 2; // move up to the parent node
        }
    }

    // delete the minimum element from the array and replace it with the last
    // element in the array, then sink it down to its correct position
    public T delMin() {
        if (isEmpty())
            throw new RuntimeException("Priority queue underflow");
        T min = pq[1];

        exch(1, n--); // exchange the minimum element with the last element in the array
        sink(1); // sink the new element at index 1 down to its correct position i.e. bubble down

        pq[n + 1] = null; // to avoid loitering
        if ((n > 0) && (n == (pq.length - 1) / 4))
            resize(pq.length / 2);
        return min;
    }

    private void sink(int k) {
        while (2 * k <= n) { // while the node at index k has at least one child

            int j = 2 * k; // the left child of the node at index k
            if (j < n && greater(j, j + 1))
                j++; // if the right child of the node at index k is less than the left child, move
                     // to the right child

            if (!greater(k, j))
                break; // if the node at index k is less than the node at index j, break

            exch(k, j); // exchange the node at index k with the node at index j
            k = j; // move down to the child node
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public T min() {
        if (isEmpty())
            throw new RuntimeException("Priority queue underflow");
        return pq[1];
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private boolean greater(int i, int j) {
        return ((Comparable<T>) pq[i]).compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public java.util.Iterator<T> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements java.util.Iterator<T> {
        private MinPQ<T> copy;

        public HeapIterator() {
            copy = new MinPQ<T>(size()); // create a new MinPQ with the same size as the original MinPQ
            for (int i = 1; i <= n; i++) {
                copy.insert((T) pq[i]); // insert all elements from the original MinPQ to the new MinPQ
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public T next() {
            if (!hasNext())
                throw new RuntimeException();
            return copy.delMin();
        }
    }
}
