package events.eventsExceptions;

public class InvalidGameStateChangeException extends InvalidConfigException{
    /**
     * Exception thrown when an unrecognized gameStateChange is listed in the config file
     */
    public InvalidGameStateChangeException(String gameStateChange) {
        super("Invalid game state change detected:" + gameStateChange);
    }
}
