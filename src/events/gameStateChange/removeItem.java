package events.gameStateChange;
import WorldOrigin.player;
import BoardPack.*;

public class removeItem extends gameStateChange {
    String item;

    /**
     * Constructs the object by adding the item that needs to be removed
     * @param item the string name of the item being removed
     */
    public removeItem(String item){this.item=item;};

    /**
     * Calls the function that removes an item from the players inventory
     * @param player the current player
     * @param board the current board
     */
    @Override
    public BoardI execute(player player, BoardI board) throws Exception{
        player.removeInventory(item); 
        return board;
    }

    /**
     * Returns the string of the way the game state change is formatted in the config file
     */
    @Override
    public String toString() {
        return "add_item["+item+"]";
    }
    
}

