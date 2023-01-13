package WorldOrigin.WorldOriginExceptions;

public class MissingItemException extends Exception{
    public MissingItemException() {
        super("Item does not exist");
    }
}
