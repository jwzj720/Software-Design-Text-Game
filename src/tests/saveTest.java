package tests;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import BoardPack.*;
import WorldOrigin.*;
import saveGame.*;

class saveTest {
   
   private BoardI boardOne;
   private player little_guy;
   private File file;
   private File exists;

   
    @Test
    void testSave() {
      //create board and player
      file = new File("src/BoardPack/dummy_map.map");
      parseBoard p = new parseBoard();
      boardOne =  p.readBoard(file);
      Location start = new Location(3,2);
      little_guy= new player(start);
      little_guy.addInventory("test");
      //save game
      save saveGame = new save(boardOne, little_guy, 0);
      File exists1 = new File("src/TAP0.txt");
      exists = exists1;
      //check if file exists
      assertTrue(exists1.exists());
    }
    @Test
    void testLoad()
    {
      File f = new File("src/TAP0.txt");
      file = new File("src/BoardPack/dummy_map.map");
      parseBoard p = new parseBoard();
      boardOne =  p.readBoard(file);
      Location start = new Location(3,2);
      little_guy= new player(start);
      little_guy.addInventory("test");
      try {
         load loadGame = new load(f, 0);
         BoardI loadedBoard = loadGame.loadBoard();
         loadedBoard.draw();
         player loadedPlayer = loadGame.loadPlayer();
      
         /*
         #TODO: implement test for board and inventory since comparing arraylists with an assertEquals isn't working
         */ 
         assertEquals(little_guy.getPlayerLoc().getX(), loadedPlayer.getPlayerLoc().getX());
         assertEquals(little_guy.getPlayerLoc().getY(), loadedPlayer.getPlayerLoc().getY());
      } catch (Exception e) {
         fail(e.toString());
      }
    }

}