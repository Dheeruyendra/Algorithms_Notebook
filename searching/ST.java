package searching;

import java.util.Iterator;
import java.util.TreeMap;

//Sorted Symbol Table
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key>{
     private TreeMap<Key, Value> st;

        public ST() {
            st = new TreeMap<Key, Value>();
        }

        public Value get(Key key){
            if(key == null) throw new IllegalArgumentException("key is null");
            return st.get(key);
        }
        public void put(Key key, Value val){
            if(key == null) throw new IllegalArgumentException("key is null");
            if(val == null) st.remove(key);
            else st.put(key, val);
        }
        public void delete(Key key){
            if(key == null) throw new IllegalArgumentException("key is null");
            st.remove(key);
        }
        public boolean contains(Key key){
            if(key == null) throw new IllegalArgumentException("key is null");
            return st.containsKey(key);
        }
        public boolean isEmpty(){
            return size() == 0;
        }
        public int size(){
            return st.size();
        }
        public Key min(){
            if(isEmpty()) return null;
            return st.firstKey();
        }
        public Key max(){
            if(isEmpty()) return null;
            return st.lastKey();
        }
        public Key floor(Key key){
            if(key == null) throw new IllegalArgumentException("key is null");
            Key k = st.floorKey(key);
            if(k == null) return null;
            return k;
        }
        public Key ceiling(Key key){
            if(key == null) throw new IllegalArgumentException("key is null");
            Key k = st.ceilingKey(key);
            if(k == null) return null;
            return k;
        }
     

        public Iterable<Key> keys(){
            return st.keySet();
        }

        public Iterator<Key> iterator(){
            return st.keySet().iterator();
        }
}
