package events.gameStateChange;

import BoardPack.*;
import WorldOrigin.player;

public class nullState extends gameStateChange {
    public nullState(){};

    /**
     * Nothing happens if a symbol with no game state change is hit
     * @param player the current player
     * @param board the current board
     */
	@Override
	public BoardI execute(player player, BoardI board) throws Exception{
        return board;
	}

    /**
     * Returns the string of the game state change in the config file
     * @return the string "null"
     */
    @Override
    public String toString() {
        return "null";
    }
}
