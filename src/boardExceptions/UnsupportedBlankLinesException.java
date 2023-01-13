package boardExceptions;

import boardExceptions.InvalidBoardException;

public class UnsupportedBlankLinesException extends InvalidBoardException {
    public UnsupportedBlankLinesException() {
        super("Blank lines are not allowed before the end of the file");
    }
}
