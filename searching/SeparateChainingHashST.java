package searching;

import javax.management.Query;
import fundamentals.Queue;

public class SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 10;
    private int n; // no of key-value pairs
    private int m; // hash table size
    private SequentialSearchST<Key, Value>[] st; // array of linked-list symbol table

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        this.n = 0;
        st = new SequentialSearchST[m]; // create m linked lists
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(Key key) {
        int i = key.hashCode(); // hash code of key
        return i % m; // index between 0 and m-1
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        int i = hash(key); 
        return st[i].get(key) != null; // check if key is present in the linked list at index i
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        int i = hash(key);
        return st[i].get(key); // get value of key from linked list at index i
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        if (val == null)
            throw new IllegalArgumentException("value is nulll");

        // double size if average length of list at each index >=10
        if (n >= 10 * m)
            resize(2 * m);

        int i = hash(key);
        st[i].put(key, val);
        n++;
    }

    public void delete(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        int i = hash(key);
        if (st[i].contains(key))
            n--;
        st[i].delete(key);

        if (m > INIT_CAPACITY && n <= 2 * m) // halve table size if average length of list <= 2
            resize(m / 2);
    }

    int size() {
        return n;
    }

    boolean isEmpty() {
        return n == 0;
    }

    private void resize(int capacity) {
        SeparateChainingHashST<Key, Value> copy = new SeparateChainingHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                copy.put(key, st[i].get(key));
            }
        }
        
        this.m = copy.m;
        this.n = copy.n;
        this.st = copy.st;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }
}
