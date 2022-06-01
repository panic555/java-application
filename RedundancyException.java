package hr.java.production.exceptions;

public class RedundancyException extends Exception{

    /**Constructor
     *
     * @param message Message that explains exception.
     */

    public RedundancyException(String message) {
        super(message);
    }

    /**Constructor
     *
     * @param message Message that explains exception.
     * @param cause Message that shows cause of exception.
     */

    public RedundancyException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Constructor
     *
     * @param cause Message that shows cause of exception.
     */

    public RedundancyException(Throwable cause) {
        super(cause);
    }

    public RedundancyException() {
    }
}
