package boardExceptions;

import boardExceptions.InvalidBoardException;

public class YDimensionDiscrepencyException extends InvalidBoardException {
    public YDimensionDiscrepencyException() {
        super("Y dimension does not match data");
    }
}
