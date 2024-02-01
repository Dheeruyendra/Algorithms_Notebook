package Sorting;

public class Quick {
    /*
     * Quick Sort Idea:
     * 1. Shuffle the array
     * 2. Partition so that, for some j
     * - entry a[j] is in place
     * - no larger entry to the left of j
     * - no smaller entry to the right of j
     * 3. Sort each piece recursively
     */

    public void sort(Comparable[] a) {
        // shuffle needed for performance guarantee
        KnuthShuffle ks = new KnuthShuffle();
        ks.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi); // partition the array
        sort(a, lo, j - 1); // sort left half
        sort(a, j + 1, hi); // sort right half
    }

    private int partition(Comparable[] arr, int lo, int hi) {
        int i = lo, j = hi + 1; // left and right scan indices
        while (true) {
            while (less(arr[++i], arr[lo])) // find item on left to swap
                if (i == hi)
                    break; // if i reaches end, break

            while (less(arr[lo], arr[--j])) // find item on right to swap
                if (j == lo)
                    break; // if j reaches start, break i.e. actually this is redundant

            if (i >= j)
                break; // check if pointers cross
            exch(arr, i, j); // swap elements because we found out of place elements
        }

        exch(arr, lo, j); // swap with partitioning item i.e. a[lo] with a[j]
        return j; // return index of item now known to be in place
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; // if v < w, return true
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    //we can also implement merge sort with comparator i.e. for custom objects with custom comparator(alternate order of sorting) 
    //similar to merge sort
}
