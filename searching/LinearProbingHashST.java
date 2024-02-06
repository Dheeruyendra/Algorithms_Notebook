package searching;

import fundamentals.Queue;

public class LinearProbingHashST<Key, Value> {

    private static final int INIT_CAPACITY = 10;

    private int n; //no of key-value pairs
    private int m; //size of probing table
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }
    public LinearProbingHashST(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    private int hash(Key key){
          int i = key.hashCode();
          return i%m;
    }

    public boolean contains(Key key){
          if(key == null) throw new IllegalArgumentException("key is null");
          return get(key) != null;
    }

    public Value get(Key key){
           if(key == null) throw new IllegalArgumentException("key is null");
           int i = hash(key);
           for(;keys[i] != null; i= (i+1)%m){
            if(keys[i].equals(key)) return vals[i];
           }
           return null;
    }
    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("key is null");
        if(!contains(key))return;

        //find positon of key
        int i = hash(key);
        while (!keys[i].equals(key)) {
            i = (i+1)%m;
        }
        keys[i] = null;
        vals[i] = null;

       //rehash all keys in that cluster
       i = (i+1)%m;
       while(keys[i] != null){
           Key keyToRehash = keys[i];
           Value valToRehash = vals[i];
           keys[i] = null;
           vals[i] = null;
           n--;

           put(keyToRehash, valToRehash);
           i = (i+1)%m;
       }

       n--;
       if(n > 0 && n <= m/10) resize(m/2);
    }
    public void put(Key key, Value val){
        if(key == null)throw new IllegalArgumentException("key is null");
        if(val == null){
            delete(key);
            return;
        }

        if(n >= m/2)resize(2*m);

        //find empty spot
        int i = hash(key);
        for(;keys[i] != null; i = (i+1)%m){
            if(keys[i].equals(key)){
                vals[i] = val;
                return;
            }
        }
       
        keys[i] = key;
        vals[i] = val;
        n++;
    }

   

    public int size(){
         return n;
    }
    boolean isEmpty(){
        return n == 0;
    }
    private void resize(int capacity){
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for(int i=0; i<m; i++){
            if(keys[i] != null){
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        this.m = temp.m;
    }

    public Iterable<Key> keys(){
           Queue<Key> queue = new Queue<>();
           for(int i=0; i<m; i++){
               if(keys[i] != null)queue.enqueue(keys[i]);
           }
           return queue;
    }

}
