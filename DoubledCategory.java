package hr.java.production.exceptions;

public class DoubledCategory extends RuntimeException{
    /**
     * DoubledCategory exception is thrown when there are two same classes entered.
     */

    /**Constructor
     *
     * @param message Messsage that explains exception.
     */
    public DoubledCategory(String message) {
        super(message);
    }

    /**Constructor
     *
     * @param message Message that explains exception.
     * @param cause Message that shows cause of exception.
     */
    public DoubledCategory(String message, Throwable cause) {
        super(message, cause);
    }

    /**Constructor
     *
     * @param cause Message that shows cause of exception.
     */

    public DoubledCategory(Throwable cause) {
        super(cause);
    }
}
