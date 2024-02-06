package sorting;

import java.util.Comparator;

public class Merge {
    /*
     * Merge Sort Idea:
     * 1. Divide array into two halves
     * 2. Recursively sort each half
     * 3. Merge two halves
     * 
     * Time complexity: O(nlogn)
     * SC: O(n) (auxiliary array)
     * 
     * Stable: Yes
     * 
     * Advantages: Can be used for sorting linked lists
     *
     */

    void sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);

        merge(arr, aux, lo, mid, hi);
    }


/*
 *  Merge Logic Explanation:
 *  1. Copy the array to an auxiliary array
 *  2. Take two pointers i and j, one for each half of the array
 *  3. Compare the elements at i and j and copy the smaller one to the original array
 *  4. Increment the pointer of the array from which the element was copied
 *  5. Repeat until both halves are exhausted
 * 
 */
    void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int i = lo, j = mid + 1;
        for (int k = 0; k <= hi; k++) {
            if (i > mid) // if left half is exhausted
                arr[k] = aux[j++];
            else if (j > hi) // if right half is exhausted
                arr[k] = aux[i++];
            else if (less(aux[j], aux[i])) // if element at j is less than element at i{this will also help in counting inversions [noOfInversions += mid - i + 1]}
                arr[k] = aux[j++];
            else
                arr[k] = aux[i++];
        }
    }

    // merge srot with comparator i.e. for custom objects with custom
    // comparator(alternate order of sorting)

    void sort(Object[] a, Comparator c) {
        Object[] aux = new Object[a.length];
        sort(a, aux, 0, a.length - 1, c);
    }

    void sort(Object[] a, Object[] aux, int lo, int hi, Comparator c) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid, c);
        sort(a, aux, mid + 1, hi, c);

        merge(a, aux, lo, mid, hi, c);
    }

    void merge(Object[] a, Object[] aux, int lo, int mid, int hi, Comparator c) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(c, aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }


    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; // if v < w, return true
    }

    public static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0; // if v < w, return true
    }
}
