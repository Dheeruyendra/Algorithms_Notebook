package searching;
import fundamentals.Queue;

public class SequentialSearchST<Key, Value> {
    private class Node{
        private Key key;
        private Value val;
        private Node next;
        Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node first;
    private int n;
    public SequentialSearchST(){
        first = null;
        n = 0;
    }

     public boolean contains(Key key){
        if(key == null)throw new IllegalArgumentException("Key is null");
        return get(key) != null;
     }

/*
*  Returns the value associated with the key in the symbol table
*  @param key the key
*  @return the value associated with the key in the symbol table
*       if the key is not in the symbol table, return null
*       @throws IllegalArgumentException if key is null
*       check each key in the symbol table for equality with the given key
*       if we find a match, return the value
*       if we don't find a match, return null
*/     
     public Value get(Key key){
         if(key == null)throw new IllegalArgumentException("key is null");
         for(Node x = first; x != null; x = x.next){
               if(key.equals(x.key))return x.val;
         }
         return null;
     }

/*
*    1. Check if the key is already in the symbol table
*    2. If the key is in the symbol table, update the value associated with the key
*    3. If the key is not in the symbol table, add the key-value pair to the symbol table 
        i.e. similar to push operation in stack
*/     
     public void put(Key key, Value val){
        if(key == null)throw new IllegalArgumentException("Key is null");
        if(val == null)throw new IllegalArgumentException("val is null");

        for(Node x = first; x!=null; x = x.next){
             if(key.equals(x.key)){
                x.val = val;
                return;
             }
        }
        first = new Node(key, val, first);
        n++;

     }

/*
*   1. Check if the key is in the symbol table
*   2. If the key is in the symbol table, delete the key-value pair
*/     
     public void delete(Key key){
        if(key == null)throw new IllegalArgumentException("Key is null");
        first = delete(first, key);
     }

 /*
 * 1. Check if the current key is in the symbol table is equal to the key to be deleted 
 * 2. If the current key is equal to the key to be deleted, return the next node i.e. similar to pop operation in stack
 * 3. If the current key is not equal to the key to be deleted, call the delete method recursively
 * 4. Return the current node
 * 5. Decrement the size of the symbol table
 */     
     private Node delete(Node x, Key key){
         if(x == null)return null;
         if(key.equals(x.key)){
            n--;
            return x.next;
         }        
 
          x.next = delete(x.next, key);
          return x;
     }
    
     public int size(){
        return n;
     }
     public boolean isEmpty(){
        return n == 0;
     }
     public Iterable<Key> keys(){
        Queue<Key> queue = new Queue();
        for(Node x = first; x != null; x = x.next){
          queue.enqueue(x.key);
        }
        return queue;
     }
}

/*
 * Time Complexity:
   * contains - O(n)
   * get - O(n)
   * put - O(n)
   * delete - O(n)
   * size - O(1)
   * isEmpty - O(1)
   * keys - O(n)
 * 
 */
