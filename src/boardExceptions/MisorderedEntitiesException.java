package boardExceptions;

public class MisorderedEntitiesException extends InvalidBoardException {
    public MisorderedEntitiesException() {
        super("Entities are not in a valid order");
    }
}
