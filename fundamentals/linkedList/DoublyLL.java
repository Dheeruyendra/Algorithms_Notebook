package fundamentals.linkedList;

import java.util.Iterator;

public class DoublyLL<T> implements Iterable<T> {

    Node<T> head; 
    Node<T> tail; 
    int size;

    DoublyLL() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node<T> {
        T val;
        Node<T> next;
        Node<T> prev;

        Node(T val) {
            this.val = val;
        }
    }

    void addAtHead(T val) {
        if (isEmpty()) {
            head = new Node<>(val);
            tail = head;
        } else {
            Node<T> newHead = new Node<>(val);
            newHead.next = head.next;
            newHead.prev = head;
            head = newHead;
        }
        size++;
    }

    void addAtTail(T val) {
        if (isEmpty())
            addAtHead(val);
        else {
            Node<T> newTail = new Node<>(val);
            newTail.prev = tail;
            tail.next = newTail;
            tail = newTail;
            size++;
        }
    }

    void addAtIndex(int index, T val) {
         if(index < 0 || index > size)
            throw new IllegalArgumentException("Index out of bounds");

        if(index == 0) addAtHead(val);
        else if(index == size) addAtTail(val);
        else{
            Node<T> x = getNode(index); // node at index
            Node<T> prevX = x.prev; // node before x
            Node<T> newNode = new Node<>(val);
            
            /*Example: 1<->2<->3 addAtIndex(1, 4) => 1<->4<->2<->3
              newNode = 4, x = 2, prevX = 1
              newNode.next = x => 4<->2
              newNode.prev = prevX => 1<->4
              prevX.next = newNode => 1<->4<->2
              x.prev = newNode => 4<->2<->3
            */
            newNode.next = x; // newNode's next is x
            newNode.prev = prevX; // newNode's prev is prevX
            prevX.next = newNode; // prevX's next is newNode
            x.prev = newNode; // x's prev is newNode
            size++;

        }    
    }

    void deleteAtIndex(int index) {
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Index out of bounds");
        
        if(index == 0){
            head = head.next;
            head.prev = null;
            if(head == null) tail = null;
        }
        else if(index == size -1){
             tail = tail.prev;
             tail.next = null;
        }
        else{
            /*
             * Example: 1<->2<->3 deleteAtIndex(1) => 1<->3
             * x = 2, prevX = 1, nextX = 3
             * prevX.next = nextX => 1<->3
             * nextX.prev = prevX => 1<->3
             */
            Node<T> x = getNode(index); // node at index
            Node<T> prevX = x.prev; // node before x
            Node<T> nextX = x.next; // node after x
            prevX.next = nextX; // prevX's next is nextX
            nextX.prev = prevX; // nextX's prev is prevX
        }   
        size--;
    }

    T get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index out of bounds");
        Node<T> x = getNode(index);
        return x.val;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Index out of bounds");

        Node<T> x = head.next;
        if (index < size / 2) { // search from the front of the list because the index is closer to the front
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else { // search from the back of the list because the index is closer to the back
            x = tail.prev;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public Iterator<T> iterator() {
        return new DoublyLLIterator();
    }

    private class DoublyLLIterator implements Iterator<T> {
        Node<T> curr = head;
        public boolean hasNext() {
            return curr != null;
        }
        public T next() {
            if(!hasNext())
                throw new IllegalArgumentException("No more elements");
            T val = curr.val;
            curr = curr.next;
            return val;
        }
    }
}
