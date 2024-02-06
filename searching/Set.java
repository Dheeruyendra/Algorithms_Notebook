package searching;

import java.util.Iterator;
import java.util.TreeSet;

public class Set<Key extends Comparable<Key>> implements Iterable<Key>{
    
    TreeSet<Key> set;

    Set(){
        set = new TreeSet<Key>();
    }

    public void add(Key key){
        if(key == null) throw new IllegalArgumentException("key is null");
        set.add(key);
    }

    public void delete(Key key){
        if(key == null) throw new IllegalArgumentException("key is null");
        set.remove(key);
    }

    public boolean contains(Key key){
        if(key == null) throw new IllegalArgumentException("key is null");
        return set.contains(key);
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return set.size();
    }

    public Key min(){
        if(isEmpty()) return null;
        return set.first();
    }

    public Key max(){
        if(isEmpty()) return null;
        return set.last();
    }

    public Key floor(Key key){
        if(key == null) throw new IllegalArgumentException("key is null");
        Key k = set.floor(key);
        if(k == null) return null;
        return k;
    }

    public Key ceiling(Key key){
        if(key == null) throw new IllegalArgumentException("key is null");
        Key k = set.ceiling(key);
        if(k == null) return null;
        return k;
    }


    public Iterable<Key> keys(){
        return set;
    }

    @Override
    public Iterator<Key> iterator() {
        return set.iterator();
    }
}
