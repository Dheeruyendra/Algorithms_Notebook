package StacksAndQueues;
import java.util.Iterator;

public class StacksUsingLinkedList<T> implements Iterable<T> { //inorder to make this class iterable we need to implement the Iterable interface and override the iterator() method
  class Node {
    T item;
    Node next;

    Node(T item) {
      this.item = item;
    }
  }

  Node first = null;
  int size = 0;

  public boolean isEmpty() {
    return first == null;
  }

  public void push(T item) {
    Node newHead = new Node(item);
    newHead.next = first;
    first = newHead;
    size++;
  }

  public T pop() {
    if (isEmpty())
      throw new RuntimeException("Stack is empty");
    T item = first.item;
    first = first.next;
    size--;
    return item;
  }

  public T peek() {
    if (isEmpty())
      throw new RuntimeException("Stack is empty");
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
                if(!hasNext()) throw new RuntimeException("Stack is empty");
                  T item = current.item;
                  current = current.next;
                  return item;
              }

              public void remove(){
                  throw new UnsupportedOperationException();
              }
          
  }

}