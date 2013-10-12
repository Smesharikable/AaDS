package Algorythms.Structures;

import Algorythms.Exceptions.StackIsEmptyException;
import Algorythms.Exceptions.StackOverflowException;
import Algorythms.Exceptions.UncorrectCapacityException;
import Algorythms.Interfaces.Stack;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @param <E> - type of object contained in Stack.
 * @author Ilys Shkuratov
 */
public class ArrayStack<E> implements Stack<E> {
    private E[] items;    
    private int top = -1;
    private int capacity = 100;
    
    public ArrayStack(Class<E> c) {
        items = (E[]) Array.newInstance(c, capacity);
    }
    
    public ArrayStack(Class<E> c, int capacity) throws UncorrectCapacityException {
        if (capacity < 0)
            throw new UncorrectCapacityException("Capacity must be a positiv value!");
        items = (E[]) Array.newInstance(c, capacity);
        this.capacity = capacity;
    }
    
    @Override
    public void push(E item) throws StackOverflowException {
        if (top > capacity - 1)
            throw new StackOverflowException("The stack is full!");
        else {
            items[++ top] = item;
        }
    }

    @Override
    public E pop() throws StackIsEmptyException {
        if (top < 0)
            throw new StackIsEmptyException("Stack is empty!");
        else {
            return  items[top --];
        }
    }

    @Override
    public E top() throws StackIsEmptyException {
        if (top < 0)
            throw new StackIsEmptyException("Stack is empty!");
        else {
            return items[top];
        }
    }

    @Override
    public boolean isempty() { return top == -1; }

    @Override
    public void clear() { top = -1; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            sb.append(items[i]).append(", ");
        }
        sb.append('.');
        return sb.toString();
    }
    
    
    public void changeCapacity(int capacity) throws UncorrectCapacityException {
        if (capacity < 0)
            throw new UncorrectCapacityException("Capacity must be a positiv value!");
        items = Arrays.copyOf(items, capacity);
        this.capacity = capacity;
    }
}
