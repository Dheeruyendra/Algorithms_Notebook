package fundamentals.stacksAndQueues;

import java.util.Iterator;

public class StacksUsingLinkedList<T> implements Iterable<T> { // inorder to make this class iterable we need to
                                                               // implement the Iterable interface and override the
                                                               // iterator() method
  class Node<T> {
    T item;
    Node<T> next;

    Node(T item) {
      this.item = item;
    }
  }

  Node<T> first = null;
  int size = 0;

  public boolean isEmpty() {
    return first == null;
  }

  /*
   * Add a new item to the top of the stack
   * 1. Create a new node
   * 2. Set the new node's next link to point to the current first node
   * 3. Set the first node to be the new node
   */
  public void push(T item) {
    Node<T> newHead = new Node<>(item);
    newHead.next = first;
    first = newHead;
    size++;
  }

  /*
   * Remove the item from the top of the stack
   * 1. Save the item from the current first node
   * 2. Set the first node to be the current first node's next node
   * 3. Return the saved item
   */
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
        throw new RuntimeException("Stack is empty");
      T item = current.item;
      current = current.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

}