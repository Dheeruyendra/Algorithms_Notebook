package sorting;

public class Heap {
    private Heap() {} // This class should not be instantiated
    void sort(Comparable[] pq) {
        int n = pq.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }


        while (n > 1) {
            exch(pq, 1, n--); // exchange the root with the last node
            sink(pq, 1, n); // restore the heap order
        }
    }

    /*
     *  1. choose the largest child
     *  2. if the child is greater than the parent, exchange the child with the parent
     *  3. repeat until the heap order is restored
     */
    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) // choose the largest child
                j++;
            if (!less(pq, k, j)) // if the child is not greater than the parent then break because the heap order is restored
                break;
            exch(pq, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Comparable[] pq, int i, int j) {
        Comparable swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }
}

/*
 *  Time Complexity: O(nlogn)
 *  Space Complexity: O(1)
 *  In-place: Yes
 *  Stable: No
 * The heap sort algorithm is not stable because it does not maintain the relative order of equal keys.
 */
