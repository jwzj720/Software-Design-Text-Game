package events.gameStateChange;

import BoardPack.*;
import WorldOrigin.player;

public class addItem extends gameStateChange{
    String item;
    public addItem(String item){this.item = item;};
    /** 
     * Adds an item to the player's inventory
     * @param player player object that is executing the action
     * @param board board object that the action is being executed on
     */
    @Override
    public BoardI execute(player player, BoardI board)  throws Exception{
        player.addInventory(this.item); 
        return board;
    }
    /**
     * @return string-ified version of the item, used only for testing purposes
     */
    @Override
    public String toString() {
        return "add_item["+item+"]";
    }
    
}


