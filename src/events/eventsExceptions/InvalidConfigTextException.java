package events.eventsExceptions;

public class InvalidConfigTextException extends InvalidConfigException{
    /*
     * Exception thrown when the flavor text of an action is improperly formatted in the config file
     */
    public InvalidConfigTextException() {
        super("Config text improperly formatted");
    }
}