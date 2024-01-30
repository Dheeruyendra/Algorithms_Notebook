package StacksAndQueues;
import java.util.Iterator;
import java.lang.Iterable;
public class QueuesUsingLinkedList<T> implements Iterable<T>{
    class Node {
        T item;
        Node next;

        Node(T item) {
            this.item = item;
            next = null;
        }
    }

    Node first = null;
    Node last = null;
    int size = 0;

    boolean isEmpty() {
        return first == null;
    }

    void enqueue(T item) {
        Node oldLast = last;
        last = new Node(item);
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    T dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        T item = first.item;
        first = first.next;
        size--;
        if (isEmpty())
            last = null;
        return item;
    }

    T peek() {
        if (isEmpty())
            throw new RuntimeException("Queue is empty");
        return first.item;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T>{
        Node current = first;
        public boolean hasNext(){
            return current != null;
        }

        public T next(){
            if(!hasNext()) throw new RuntimeException("Queue is empty");
            T item = current.item;
            current = current.next;
            return item;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

}
