package Searching;

public class BinarySearch {
 /* Given sorted array of 0's and 1's find first occurence of last occurrence of 0 and first occurence of 1
    arr[] = [0, 0, 0, 0, 0, 1, 1, 1, 1]
    res[] = [4, 5]

    Time Complexity : O(log N)
    Space Complexity : O(1)
 */
    int[] BinarySearch(int[] arr){
         int n = arr.length;
         int l = -1;
         int r = n;

         while(l+1 < r){
              int mid = l+(r-l)/2;
              if(arr[mid] == 0) l = mid;
              else r = mid;
         }

         return new int[]{l, r};
    }
}
