package sorting;

import java.util.Comparator;

public class Insertion {
/*
 *  At each iteration, insertion sort picks one element from the input data, 
 * finds the location it belongs within the sorted list, and inserts it there. 
 * It repeats until no input elements remain.
 * 
 * Worst case performance: O(n^2) comparisons, swaps
 * Best case performance: O(n) comparisons, O(1) swaps
 * Average case performance: O(n^2) comparisons, swaps
 * 
 * Worst case space complexity: O(n) total, O(1) auxiliary
 * 
 * Stable: Yes
 * 
 * Advantages:
 * 1. Simple implementation
 * 2. Efficient for small data sets
 * 3. Adaptive i.e. efficient for data sets that are already substantially sorted
 * 4. Stable i.e. does not change the relative order of elements with equal keys
 * 5. In-place i.e. only requires a constant amount O(1) of additional memory space
 * 
 */

 /*
  *  check if the current element is less than the previous element, if yes, swap them
  *  
  */
    private Insertion(){}

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1]))
                    exch(arr, j, j - 1);
                else
                    break;
            }
        }
    }

    public static void sort(Object[] arr, Comparator c){
          int n = arr.length;
          for(int i=1; i<n; i++){
            for(int j=i; j > 0; j--){
                if(less(c, arr[j], arr[j-1]))exch(arr, i, j);
                else break;
            }
          }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0; // if v < w, return true
    }

    public static boolean less(Comparator c, Object v, Object w){
           return c.compare(v, w) < 0;
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
