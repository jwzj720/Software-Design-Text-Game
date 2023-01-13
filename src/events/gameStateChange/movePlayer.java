package events.gameStateChange;
import java.util.ArrayList;

import BoardPack.*;
import WorldOrigin.player;
import events.event;

public class movePlayer extends gameStateChange {
    int[] coordinates;
    ArrayList<event> possibleEvents = new ArrayList<event>();//
    public movePlayer(int[] coordinates){this.coordinates=coordinates;}
    /**
     * Calls the previously defined move function to move the player
     * @param player player object that is executing the action
     * @param board board object that the action is being executed on
     */
    @Override
    public BoardI execute(player player, BoardI board) throws Exception {
        Location loc = new Location(this.coordinates[0],this.coordinates[1]);
        BoardI boardOne = BoardPack.moveAction.move(player,loc,board,possibleEvents);
        return boardOne;
    }
    /** 
     * @return string-ified version of the gameStateChange, used only for testing purposes
    */
    @Override
    public String toString() {
        return "move["+coordinates[0]+","+coordinates[1]+"]";
    };
}
