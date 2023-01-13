package boardExceptions;

public class BadDimensionValuesException extends InvalidBoardException {
    public BadDimensionValuesException() {
        super("One of the dimensions is not a positive integer");
    }
}
