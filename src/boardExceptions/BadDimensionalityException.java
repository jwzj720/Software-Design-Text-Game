package boardExceptions;

public class BadDimensionalityException extends InvalidBoardException {
    public BadDimensionalityException() {
        super("Dimension line is incorrectly formatted");
    }
}
