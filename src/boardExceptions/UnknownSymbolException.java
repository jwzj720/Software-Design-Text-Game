package boardExceptions;

import boardExceptions.InvalidBoardException;

public class UnknownSymbolException extends InvalidBoardException {
    public UnknownSymbolException() {
        super("Unknown symbol in board data");
    }
}
