package Algorythms.Exceptions;

/**
 *
 * @author Ilys Shkuratov
 */
public class UncorrectCapacityException extends Exception {

    /**
     * Creates a new instance of
     * <code>UncorrectCapacityException</code> without detail message.
     */
    public UncorrectCapacityException() {
    }

    /**
     * Constructs an instance of
     * <code>UncorrectCapacityException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public UncorrectCapacityException(String msg) {
        super(msg);
    }
}
