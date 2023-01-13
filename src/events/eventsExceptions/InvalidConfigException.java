package events.eventsExceptions;

public class InvalidConfigException extends Exception {
    /**
     * General purpose exception thrown when there is an error with the config file
     */
    public InvalidConfigException(String msg) {
        super(msg);
    }
}