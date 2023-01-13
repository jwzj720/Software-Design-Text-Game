package events.eventsExceptions;

public class InvalidConfigSymbolException extends InvalidConfigException{
    /**
     * Exception thrown when a symbol is improperly formatted in the config file
     */
    public InvalidConfigSymbolException() {
        super("Config symbol improperly formatted");
    }
}
