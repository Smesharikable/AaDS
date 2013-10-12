package Algorythms.Interfaces;

import Algorythms.Exceptions.StackOverflowException;
import Algorythms.Exceptions.StackIsEmptyException;

/**
 *
 * @param <E> - type of object contained in the stack.
 * @author Ilya Shkuratov
 */
public interface Stack<E> {
    
    /**
     * Insert specified item into the stack.
     * @param item - object to insert.
     * @throws StackOverflowException  
     */
    public void push(E item) throws StackOverflowException;
    
    /**
     * Removes the object at the top od this stack
     * and returns this object as the value. 
     * @return
     * @throws StackIsEmptyException  
     */
    public E pop() throws StackIsEmptyException;
    
    /**
     * Returns the object at the top of stack without removing.
     * @return object at the top of stack
     * @throws StackIsEmptyException  
     */
    public E top() throws StackIsEmptyException;
    
    /**
     * Checks if the stack is empty.
     * @return true if stack is empty, false if it is not.
     */
    public boolean isempty();
    
    /**
     * Delete all objects from the stack.
     */
    public void clear();
    
}
