package sorting;

public class KnuthShuffle {
    /*
     * Knuth Shuffle Idea:
     * 1. In iteration i, pick integer r between 0 and i uniformly at random
     * 2. Swap a[i] and a[r]
     *
     * Time complexity: O(n)
     * SC: O(1)
     * Advantages: 1. Uniform random permutation (every permutation equally likely)
     *             2. Helps in quicksort for avoiding worst case
     */

    public static void shuffle(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = uniform(i + 1);
            exch(a, i, r);
        }
    }

    public static void shuffle(Object[] a){
         int n = a.length;
         for(int i=0; i<n; i++){
            int r = uniform(i+1);
            exch(a, i, r);
         }
    }

    public static void exch(Object[] a, int i, int j){
          Object swap = a[i];
          a[i] = a[j];
          a[j] = swap;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static int uniform(int n) {
        return (int) Math.floor(Math.random() * n);
    }
}