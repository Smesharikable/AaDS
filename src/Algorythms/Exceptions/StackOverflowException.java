package Algorythms.Exceptions;

/**
 *
 * @author Ilya Shkuratov
 */
public class StackOverflowException extends Exception {

    /**
     * Creates a new instance of
     * <code>StackOverflowException</code> without detail message.
     */
    public StackOverflowException() {
    }

    /**
     * Constructs an instance of
     * <code>StackOverflowException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public StackOverflowException(String msg) {
        super(msg);
    }
}
