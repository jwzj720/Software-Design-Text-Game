package gameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import BoardPack.*;
import WorldOrigin.player;
import events.event;
import saveGame.load;
import saveGame.save;

public class helpers {
    /** 
     * Searches the board for the player symbol "B", returns its location
     * @param board the board the player moves on
     * @return Location of player
     */
    public static Location getPlayerLocation(BoardI board){
        for(int i=0; i < board.getNumCols(); i++)
            for(int j=0; j< board.getNumRows();j++){
                Location l = new Location(i, j);
                if(board.get(l).equals('B'))
                    return l;
            }
        return null;
    }

     /**
     * Checks if the map file exists
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public static File checkForMapFile(String filename) throws FileNotFoundException {
        filename = System.getProperty("user.dir") + "/" + filename;
        File mapFile = new File(filename);
        return mapFile;
    }

    /**
     * Checks if the load file exists
     * @param filename
     * @return load object
     */
    public static load checkForLoadFile(String filename){
        load loadSave = null;
        try{
            filename = "src/BoardPack/" + filename;
            File loadFile = new File(filename);
            loadSave = new load(loadFile, 1);
        }catch (FileNotFoundException e){
            System.out.println("Enter name of map file: ");
            return null;
        }
        return loadSave;
    }
    /**
     * Instantiates a new board depending on the presence of a load file
     * @param loadSave load object 
     * @param mapFile file containing the map'
     * @return board object
     */
    public static BoardI instantiateBoard(load loadSave,File mapFile) throws Exception{
        BoardI boardOne = null;
        if(loadSave == null){
            boardOne = BoardPack.parseBoard.readBoard(mapFile);
            boardOne.draw();
        }else{
            boardOne = loadSave.loadBoard();
            boardOne.draw();
        }
        return boardOne;
    }
    /**
     * Instantiates the player object based on if a save file is present or not
     * @param loadSave the load file used
     * @param board
     * @return player object
     * @throws Exception getPlayerLocation throws an exception
     */
    public static player instantiatePlayer(load loadSave, BoardI board) throws Exception{
        player player = null;
        if(loadSave == null){
            Location start = helpers.getPlayerLocation(board);
            player = new player(start);
        }else
            player = loadSave.loadPlayer();    
        
        return player;
    }
    /**
     * Consitutes the main game loop, asking for inputs, showing results of inputs
     * @param board
     * @param events list of possible events
     * @param player
     * @param walkableChar the char on the map that is walkable by the player
     * @throws Exception BufferedReader and move throw exceptions
     */
    public static void beginGameLoop(BoardI board, ArrayList<event> events, player player,char walkableChar) throws Exception{
        System.out.println("Use wasd to move");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while (line.equalsIgnoreCase("quit") == false) {
            line = in.readLine();
            switch(line){
                case "w":{
                    BoardPack.moveAction.move(player, new Location(0, -1),board, events);
                    break;
                }
                case "s":{
                    BoardPack.moveAction.move(player, new Location(0, 1),board, events);
                    break;
                }
                case "d":{
                    BoardPack.moveAction.move(player, new Location(1, 0),board, events);
                    break;
                }
                case "a":{
                    BoardPack.moveAction.move(player, new Location(-1, 0),board, events);
                    break;
                }
                case "save":
                    save newSave = new save(board, player, walkableChar);
            }
        }
        in.close();
    }

}
