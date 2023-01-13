package events.gameStateChange;

import WorldOrigin.player;
import BoardPack.Board;
import BoardPack.BoardI;

public class loseState extends gameStateChange {
    public loseState(){};
    /**
     * Prints out a message and exits the program
     * @param player player object that is executing the action
     * @param board board object that the action is being executed on
     */ 
    @Override
    public BoardI execute(player player, BoardI board) throws Exception {
        System.out.println("You lose!");
        System.exit(100);
        return board;
    }
    /** 
     * @return string-ified version of the gameStateChange, used only for testing purposes
    */
    @Override
    public String toString() {
        return "lose";
    }
    
    
}
