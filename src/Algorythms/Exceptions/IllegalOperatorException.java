/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorythms.Exceptions;

/**
 *
 * @author m11sia
 */
public class IllegalOperatorException extends Exception {

    /**
     * Creates a new instance of
     * <code>IllegalOperatorException</code> without detail message.
     */
    public IllegalOperatorException() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalOperatorException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalOperatorException(String msg) {
        super(msg);
    }
}
