package boardExceptions;

import boardExceptions.InvalidBoardException;

public class XDimensionDiscrepencyException extends InvalidBoardException {
    public XDimensionDiscrepencyException() {
        super("X dimension does not match data");
    }
}
