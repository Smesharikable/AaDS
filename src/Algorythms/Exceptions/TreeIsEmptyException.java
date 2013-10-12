/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorythms.Exceptions;

/**
 *
 * @author m11sia
 */
public class TreeIsEmptyException extends Exception {

    /**
     * Creates a new instance of
     * <code>TreeIsEmptyException</code> without detail message.
     */
    public TreeIsEmptyException() {
    }

    /**
     * Constructs an instance of
     * <code>TreeIsEmptyException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public TreeIsEmptyException(String msg) {
        super(msg);
    }
}
