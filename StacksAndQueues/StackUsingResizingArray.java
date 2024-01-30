package StacksAndQueues;

import java.lang.Iterable;
import java.util.Iterator;

public class StackUsingResizingArray implements Iterable<String> {
     private int initialCapacity = 8;
     private String[] stack;
     private int N;

        public StackUsingResizingArray(){
            stack = new String[initialCapacity]; //generic array creation not allowed in Java hence we use String[]
            N = 0;
        }

    public boolean isEmpty() {
            return N == 0;
    }
    public void push(String item) {
        if (N == stack.length) resize(2 * stack.length); // Double size of array when array is full
        stack[N++] = item;
    }

    public String pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        String item = stack[--N];
        stack[N] = null; // Avoid loitering i.e. holding a reference to an object when it is no longer needed
        if (N > 0 && N == stack.length/4) resize(stack.length/2); // Halve size of array when array is one-quarter full
        return item;
    }

    public String peek(){
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return stack[N-1]; // Return top element of stack, stack[N-1
    }

    public int size(){
        return N;
    }

    void resize(int newCapacity){
        String[] copy = new String[newCapacity]; // Create new array of size newCapacity
        for (int i = 0; i < N; i++)
            copy[i] = stack[i];
        stack = copy; // Assign stack to point to new array
    }

    public Iterator<String> iterator(){ //inorder to make this class iterable we need to implement the Iterable interface and override the iterator() method
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<String>{ //nested class to provide iterator implementation
        private int i = N;
        public boolean hasNext(){
            return i > 0;
        }
        public String next(){
            if(!hasNext()) throw new RuntimeException("Stack is empty");
            return stack[--i];
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }

//generic array creation not allowed in Java, we can technically do this: T[] stack = (T[]) new Object[initialCapacity]; but this is not recommended
}
