package fundamentals;

public class BinarySearch {

   private BinarySearch(){ }
   
   /*
    * Given sorted array of distinct integers, this method returns the index of the key in the array if it exists, otherwise -1.
    * @param arr : array of distinct integers sorteed in ascending order
    * @param key : the integer to be searched 
    */
   static int indexOf(int[]arr, int key){
           int lo = 0;
           int hi = arr.length - 1;

           while (lo<=hi) { // while the search space is not empty
               int mid = lo + (hi - lo) / 2; // calculate the mid index
               if (key < arr[mid]) hi = mid - 1; // if the key is less than the mid element, search the left half
               else if (key > arr[mid]) lo = mid + 1; // if the key is greater than the mid element, search the right half
               else return mid; // if the key is equal to the mid element, return the index
           }
          return -1; // if the key is not found, return -1 
   }
   
   /*
    * Given sorted array of distinct integers, this method returns the index of the first and last occurence of the key in the array if it exists, otherwise -1.
    * @param arr : array of distinct integers sorteed in ascending order
    * @param key : the integer to be searched 
    */
   static int[] indexOfAll(int[]arr, int key){
            int firstOccurence = firstOccurence(arr, key);
            int lastOccurence = lastOccurence(arr, key);
            return new int[]{firstOccurence, lastOccurence};     
   }

   static int firstOccurence(int[] arr, int key){
                 int lo = -1;
                 int hi = arr.length;

                 while (lo+1 < hi) {
                      int mid = lo+(hi-lo)/2; 
                      if(arr[mid] < key) lo = mid;
                      else hi = mid;
                 }
                 // if the key is not found, return -1
                 int result = (hi == arr.length || arr[hi] != key) ? -1 : hi; //all the elements are less than the key or the key is not found
                 return result;
   }

   static int lastOccurence(int[] arr, int key){
              int lo = -1;
              int hi = arr.length;

              while(lo + 1 < hi){
                int mid = lo+(hi-lo)/2;
                if(arr[mid] <= key) lo = mid;
                else hi = mid; 
              }
               // if the key is not found, return -1
               int result = (lo == -1 || arr[lo] != key) ? -1 : lo; //all the elements are greater than the key or the key is not found
               return result;
   }
}
/*
 * Time Complexity : O(log n)
 * Space Complexity : O(1)
 * 
 */
