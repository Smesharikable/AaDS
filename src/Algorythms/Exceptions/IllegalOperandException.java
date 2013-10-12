/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorythms.Exceptions;

/**
 *
 * @author m11sia
 */
public class IllegalOperandException extends Exception {

    /**
     * Creates a new instance of
     * <code>IllegalOperandException</code> without detail message.
     */
    public IllegalOperandException() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalOperandException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalOperandException(String msg) {
        super(msg);
    }
}
