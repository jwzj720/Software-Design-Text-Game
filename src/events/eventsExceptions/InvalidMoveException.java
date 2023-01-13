package events.eventsExceptions;

public class InvalidMoveException extends InvalidConfigException{
    /**
     * Exception thrown when a move gameStateChange is improperly formatted
     */
    public InvalidMoveException() {
        super("Invalid move given");
    }
    
}
