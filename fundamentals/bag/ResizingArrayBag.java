package fundamentals.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<T> implements Iterable<T> {
    private static int init_capacity = 10;
    T[] arr;
    int n;

    ResizingArrayBag() {
        arr = (T[]) new Object[init_capacity];
        n = 0;
    }

    public void add(T item) {
        if (n == arr.length)
            resize(2 * n);
        arr[n++] = item;
    }

    int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public T next(){
                if(!hasNext())throw new NoSuchElementException();
                return arr[i++]
            }
    }
}
