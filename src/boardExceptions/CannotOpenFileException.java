package boardExceptions;

public class CannotOpenFileException extends InvalidBoardException {
    public CannotOpenFileException() {
        super("File exists but cannot be read");
    }
}
