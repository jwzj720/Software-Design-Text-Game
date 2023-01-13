// The gameEngine serves as the central processor for all of TAP's subsystems
package gameEngine;
import events.*;
import saveGame.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import BoardPack.Board;
import BoardPack.BoardI;
import BoardPack.Location;
import BoardPack.moveAction;
import BoardPack.parseBoard;
import WorldOrigin.player;

public class gameEngine{
    public static void main(String[] args) throws Exception{
        char walkableChar = '.';
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Get config file
        System.out.println("Enter config file name: ");
        String filename = "src/config/" + in.readLine();
        ArrayList<String> lines = parseConfigFile.readConfigFile(filename);
        ArrayList<event> events = parseConfigFile.parseConfigLines(lines);

        // Get map file
        System.out.println("If you have a saved game, please type file name: ");
        load loadSave = helpers.checkForLoadFile(in.readLine());
        File mapFile = helpers.checkForMapFile(in.readLine()); 

        BoardI board = helpers.instantiateBoard(loadSave, mapFile);
        player player = helpers.instantiatePlayer(loadSave, board);
        helpers.beginGameLoop(board, events, player, walkableChar);
    }
 }