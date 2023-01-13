package events.eventsExceptions;

public class InvalidConfigFormatException extends Exception {
    /*
     * Exception thrown when there is a problem with the way the config file is ordered/formatted
     */
    public InvalidConfigFormatException(String msg) {
        super(msg);
    }
}