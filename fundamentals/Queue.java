package fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
       private Node<T> front;
       private Node<T> rear;
       private int n;

       private class Node<T>{
           private T item;
           private Node<T> next;
           Node(T item){
            this.item = item;
           }
       }
       public Queue(){
        front = null;
        rear = null;
        n = 0;
       }

      public void enqueue(T item){
          if(item == null)throw new IllegalArgumentException("item is null");
          Node<T> oldrear = rear;
          rear = new Node<T>(item);
          if(isEmpty()) front = rear;
          else oldrear.next = rear;
          n++;
       }
       public T dequeue(){
          if(isEmpty())throw new NoSuchElementException("Queue is empty");
          T item = front.item;
          front = front.next;
          n--;
          if(isEmpty()) rear = null;
          return item;
       }
       public int size(){
        return n;
       }
       public boolean isEmpty(){
         return n == 0;
       }
       public Iterator<T> iterator(){
          return new LinkedListIterator(front);
       }

       private class LinkedListIterator implements Iterator<T>{
               private Node<T> x;
               LinkedListIterator(Node<T> front){
                       x = front;
               }
               public boolean hasNext(){
                return x != null;
               }
               public T next(){
                 if(!hasNext())throw new NoSuchElementException("queue is empty");
                 T item = x.item;
                 x = x.next;
                 return item;
               }
       }
}

/*
 * Time Complexity
 * enqueue - O(1)
 * dequeue - O(1)
 * size - O(1)
 * isEmpty - O(1)
 * 
 */
