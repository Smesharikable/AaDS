package Algorythms.Exceptions;

/**
 *
 * @author Ilys Shkuratov
 */
public class IllegalCharacterException extends Exception {

    /**
     * Creates a new instance of
     * <code>IllegalCharacterException</code> without detail message.
     */
    public IllegalCharacterException() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalCharacterException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IllegalCharacterException(String msg) {
        super(msg);
    }
}
