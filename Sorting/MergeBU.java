package Sorting;

public class MergeBU {
    /*
     * Merge Sort Bottom Up Idea:
     * 1. Pass through array, merging subarrays of size 1
     * 2. Repeat for subarrays of size 2, 4, 8, 16 and so on
     * 
     * Time complexity: O(nlogn)
     * SC: O(n) (auxiliary array)
     * 
     * Stable: Yes
     * 
     * Advantages: No recursion needed
     */

    void sort(Comparable[] arr) {
        int n = arr.length;
        Comparable[] aux = new Comparable[n];
        for (int sz = 1; sz < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(arr, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
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

    boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; // if v < w, return true
    }

}
