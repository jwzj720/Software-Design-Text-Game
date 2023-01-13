package boardExceptions;

public class LocationOffBoardException extends InvalidBoardException {
    public LocationOffBoardException() {
        super("Attempt to access a location outside of the board");
    }
}
