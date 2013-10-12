package Algorythms.Exceptions;

/**
 *
 * @author Ilya Shkuratov
 */
public class IllegalSignPositionException extends Exception {

    /**
     * Creates a new instance of
     * <code>IllegalSignPositionException</code> without detail message.
     */
    public IllegalSignPositionException() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalSignPositionException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalSignPositionException(String msg) {
        super(msg);
    }
}
