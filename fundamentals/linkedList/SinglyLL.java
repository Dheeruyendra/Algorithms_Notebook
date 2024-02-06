package fundamentals.linkedList;

import java.util.Iterator;

public class SinglyLL<T> implements Iterable<T> {

    Node<T> head;
    int size;

    private static class Node<T> {
        T val;
        Node<T> next;

        Node(T val) {
            this.val = val;
            this.next = null;
        }
    }

    public SinglyLL() {
        head = null;
        size = 0;
    }

    T get(int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException("Index out of bounds");
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.val;
    }

    Node<T> getNode(int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException("Index out of bounds");
        Node<T> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    void deleteAtIndex(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index out of bounds");

        if (index == 0)
            head = head.next;
        else {
            Node<T> prev = getNode(index - 1);
            prev.next = prev.next.next;
        }
    }

    void addAtIndex(int index, T val) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Index out of bounds");
        if (index == 0)
            addAtHead(val);
        else {
            Node<T> newNode = new Node<>(val);
            Node<T> prev = getNode(index - 1);
            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }
    }

    void addAtHead(T val) {
        Node<T> newHead = new Node(val);
        newHead.next = head;
        head = newHead;
        size++;
    }

    void addAtTail(T val) {
        if (isEmpty())
            addAtHead(val); // if the list is empty, add at head (i.e. index 0
        Node<T> prev = getNode(size - 1);
        Node<T> newNode = new Node<>(val);
        prev.next = newNode;
        size++;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if(!hasNext())
                throw new IllegalArgumentException("No more elements to iterate");
            T item = current.val;
            current = current.next;
            return item;
        }
    }
}
