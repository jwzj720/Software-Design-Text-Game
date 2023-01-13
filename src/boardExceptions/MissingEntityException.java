package boardExceptions;

public class MissingEntityException extends InvalidBoardException {
    public MissingEntityException() {
        super("Alex, Player, or Tea are missing");
    }
}
