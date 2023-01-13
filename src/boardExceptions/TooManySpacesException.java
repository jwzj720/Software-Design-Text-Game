package boardExceptions;

import boardExceptions.InvalidBoardException;

public class TooManySpacesException extends InvalidBoardException {
    public TooManySpacesException() {
        super("Items on a line should be separated by only a single space");
    }
}
