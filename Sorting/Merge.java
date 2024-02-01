package Sorting;

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

    void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }

        int i = lo, j = mid + 1;
        for (int k = 0; k <= hi; k++) {
            if (i > mid)
                arr[k] = aux[j++];
            else if (j > hi)
                arr[k] = aux[i++];
            else if (less(aux[j], aux[i]))
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
            else if (less(aux[j], aux[i], c))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; // if v < w, return true
    }

    public static boolean less(Object v, Object w, Comparator c) {
        return c.compare(v, w) < 0; // if v < w, return true
    }

}
