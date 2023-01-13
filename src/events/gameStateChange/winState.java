package events.gameStateChange;

import WorldOrigin.player;

import BoardPack.*;

public class winState extends gameStateChange{
    public winState(){};
	
    /**
     * Prints a win statement and exits the game when the player wins
     * @param player the current player
     * @param board the current board for the game
     */
    @Override
	public BoardI execute(player player, BoardI board) {
		System.out.println("You win!");
        System.exit(100);
        return board;
	}
    
    /**
     * Returns the string that corresponds to the game state change
     * @return String
     */
    @Override
    public String toString() {
        return "win";
    }
    
}
