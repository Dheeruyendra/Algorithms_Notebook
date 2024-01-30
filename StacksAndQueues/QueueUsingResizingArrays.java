package StacksAndQueues;
import java.lang.Iterable;
import java.util.Iterator;
public class QueueUsingResizingArrays<T> implements Iterable<T> {
       private static int initialCapacity = 8;
       T[] arr;
       int front, rear;
       int size;

         public QueueUsingResizingArrays() {
              arr = (T[]) new Object[initialCapacity];
              front = rear  = size = 0;
         }
         public boolean isEmpty() {
             return size == 0;
         }
         public void enqueue(T item) {
             if (size == arr.length) resize(2 * arr.length); // Double size of array when array is full
             arr[rear++] = item;
             rear %= arr.length; // wrap-around
             size++;
         }

         public T dequeue() {
             if (isEmpty()) throw new RuntimeException("Queue is empty");
             T item = arr[front];
             arr[front] = null; // Avoid loitering i.e. holding a reference to an object when it is no longer needed
             front++;
             front %= arr.length; // wrap-around
             size--;
             if (size > 0 && size == arr.length/4) resize(arr.length/2); // Halve size of array when array is one-quarter full
             return item;
         }

        public T peek(){
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            return arr[front]; // Return top element of stack, stack[N-1
        }

        public int size(){
            return size;
        }

        private void resize(int newCapacity){
            T[] copy = (T[]) new Object[newCapacity]; // Create new array of size newCapacity
            for (int i = 0; i < size; i++)
                copy[i] = arr[(front + i) % arr.length];
            arr = copy; // Assign stack to point to new array
            front = 0;
            rear = size;
        }

        public Iterator<T> iterator(){ //inorder to make this class iterable we need to implement the Iterable interface and override the iterator() method
            return new ArrayIterator();
        }

        private class ArrayIterator implements Iterator<T>{ //nested class to provide iterator implementation
            private int i = front;
            public boolean hasNext(){
                return i < size;
            }
            public T next(){
                if(!hasNext()) throw new RuntimeException("Queue is empty");
                T item = arr[i++];
                i %= arr.length; // wrap-around
                return item;
            }
        }

}
