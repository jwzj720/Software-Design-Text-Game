package events.eventsExceptions;

public class InvalidConfigActionException extends InvalidConfigException{
    /**
     * Exception thrown when an action is improperly formatted in the config file
     */
    public InvalidConfigActionException() {
        super("Config actions improperly formatted");
    }
}
