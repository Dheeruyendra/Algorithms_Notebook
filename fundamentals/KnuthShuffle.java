package fundamentals;
/*
 *  Rearranges an array of n elements in uniformly random order
 *  (under the assumption that Math.random() generates independent and uniformly distributed numbers between 0 and 1).
 */
public class KnuthShuffle {
    private KnuthShuffle() { }

    void shuffle(Object[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int r = (int) (Math.random() * (i + 1)); // generate a random number between 0 and i
            Object temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
        }
    }
}

/*
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
