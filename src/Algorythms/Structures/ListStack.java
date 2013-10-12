package Algorythms.Structures;

import Algorythms.Exceptions.StackIsEmptyException;
import Algorythms.Interfaces.Stack;

/**
 *
 * @param <E>  - type of object constained in stack.
 * @author 1
 */
public class ListStack<E> implements Stack<E> {
    private ListElement<E> top;
    
    public ListStack() {}
    
    /**
     * 
     * @param item - object that will be at top of the stack.
     */
    public ListStack(E item) {
        top = new ListElement<E>(item);
    }
    
    @Override
    public void push(E item) {
        ListElement buf = new ListElement(item);
        buf.next = top;
        top = buf;
    }

    
    @Override
    public boolean isempty() {
        return top == null ? true : false;
    }

    @Override
    public void clear() { top = null; }

    @Override
    public E pop() throws StackIsEmptyException {
        if (top == null)
            throw new StackIsEmptyException("Stack is empty");
        ListElement<E> buf = top;
        top = top.next;
        return buf.value;
    }

    @Override
    public E top() throws StackIsEmptyException {
        if (top == null)
            throw new StackIsEmptyException("Stack is empty");
        return top.value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListElement cur = top;
        while (cur != null) {
            sb.append(cur.value).append(", ");
            cur = cur.next;
        }
        sb.append('.');
        return sb.toString();
    }
    
    
    private class ListElement<E> {
        E value;
        ListElement<E> next;
        
        public ListElement() {}
        public ListElement(E value) {
            this.value = value;
        }
    }
    
}
