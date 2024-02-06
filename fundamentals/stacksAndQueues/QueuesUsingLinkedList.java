package fundamentals.stacksAndQueues;

import java.util.Iterator;
import java.lang.Iterable;

public class QueuesUsingLinkedList<T> implements Iterable<T> {
    class Node<T> {
        T item;
        Node<T> next;

        Node(T item) {
            this.item = item;
            next = null;
        }
    }

    Node<T> first = null;
    Node<T> last = null;
    int size = 0;

    boolean isEmpty() {
        return first == null;
    }

    /*
     * Add a new item to the end of the queue
     * 1. Save a link to the last node
     * 2. Create a new node
     * 3. Set the next link of the last node to point to the new node
     * 4. Set the last node to be the new node
     * 5. If the queue was empty, set the first node to be the new node
     */
    void enqueue(T item) {
        Node<T> oldLast = last;
        last = new Node<>(item);
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    /*
     * Remove the item from the front of the queue
     * 1. Save the item from the first node
     * 2. Set the first node to be the current first node's next node
     * 3. Decrement the size of the queue
     * 4. If the queue is empty, set the last node to be null
     */
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

    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        Node<T> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext())
                throw new RuntimeException("Queue is empty");
            T item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}