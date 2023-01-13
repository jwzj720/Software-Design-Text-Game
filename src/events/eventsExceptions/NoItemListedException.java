package events.eventsExceptions;

public class NoItemListedException extends InvalidConfigException{
    /**
     * Exception thrown when no item is given for add or remove item
     */
    public NoItemListedException() {
        super("No item given for add_item or remove_item");
    }
}
