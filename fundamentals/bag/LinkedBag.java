package fundamentals.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedBag<T> implements Iterable<T>{
    class Node<T>{
        T item;
        Node<T> next;
        Node(T item){
            this.item = item;
            next = null;
        }
    }
    private Node<T> first;
    private int n; //no of elements in the bag

    public LinkedBag(){
        first = null;
        n = 0;
    }

    public void add(T item){
        Node<T> oldFirst = first;
        first = new Node<T>(item);
        first.next = oldFirst;
        n++;
    }

    public boolean isEmpty(){
        return first == null;
    }
    public int size(){
        return n;
    }

    public Iterator<T> iterator(){
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<T>{
            private Node<T> curr;
            public ListIterator(Node<T> first){
                curr = first;
            }
            public boolean hasNext(){
                 return curr != null;
            }
 
            public T next(){
                if(!hasNext())throw new NoSuchElementException();
                T item = curr.item;
                curr = curr.next;
                return item;
            }

    }

}
