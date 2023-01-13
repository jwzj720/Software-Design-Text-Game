package events.gameStateChange;

import BoardPack.*;
import WorldOrigin.player;

abstract public class gameStateChange {
    /**
     * Exexcutes the given gameStateChange
     * @param player player object that is executing the action
     * @param board board object that the action is being executed on
     */
    public BoardI execute(player player, BoardI board) throws Exception{
        return board;};
    /**
     *  @return string-ified version of the gameStateChange, used only for testing purposes
     */
    public String toString(){return null;};
}
