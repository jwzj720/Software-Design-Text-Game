package boardExceptions;

public class MissingFileException extends InvalidBoardException {
    public MissingFileException() {
        super("Could not find the map file");
    }
}
