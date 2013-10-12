package Algorythms.Exceptions;

/**
 *
 * @author Ilya Shkuratov
 */
public class StackIsEmptyException extends Exception {

    /**
     * Creates a new instance of
     * <code>StackIsEmptyException</code> without detail message.
     */
    public StackIsEmptyException() {
    }

    /**
     * Constructs an instance of
     * <code>StackIsEmptyException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public StackIsEmptyException(String msg) {
        super(msg);
    }
}
