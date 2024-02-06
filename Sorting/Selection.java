package sorting;

import java.util.Comparator;

public class Selection {
    /*
     *
     * Selection Sort Idea:
     * 1. Find the smallest item in the array
     * 2. Exchange it with the first entry
     * 3. Find the next smallest item and exchange it with the second entry and so
     * on
     * 
     * Time complexity: O(n^2)
     * SC: O(1) (in place)
     * 
     * Stable: No
     * Advantages: 1. Number of exchanges is minimal
     */

    public void sort(Comparable[] a) { // takes comparable array as input and sorts it i.e. objects of any class that
                                       // implements comparable interface
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min]))
                    min = j; // if current element is less than min, update min
            }
            exch(a, i, min);
        }
    }

    public void sort(Object[] a, Comparator c) { // takes object array and comparator as input and sorts it
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(c, a[j], a[min]))
                    min = j; // if current element is less than min, update min
            }
            exch(a, i, min);
        }
    }

    public static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0; // if v < w, return true
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; // if v < w, return true
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

}
