package saveGame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import BoardPack.*;
import WorldOrigin.*;

public class save {
   private int count;
   
   /**
    * save saves the current state of game
    * @param board  board to save
    * @param player  player to save
    * @param c  the count of saves (NOT IMPLEMENTED)
    */
   public save(BoardI board, player player, int c)
   {
      try{
      saveGame(board, player, c);
      }catch(Exception e)
      {
         e.getMessage();
         System.exit(1);
      }
   }
   
  
   /**
    * saveGame writes the parameters to a file to be loaded later

    * @param board  the board to be saved
    * @param player  the player to be saved
    * @param c  the count of the save file (NOT IMPLEMENTED)
    * @throws Exception if file can't be loaded, or if board is invalid
    */
   public void saveGame(BoardI board, player player, int c) throws Exception{
   FileWriter f = new FileWriter(new File("TAP" + count + ".txt"));
   BufferedWriter write = new BufferedWriter(f);
   count = c;
   //draw board into file
   board.saveGame(write);
   write.flush();
   //write.newLine();
   //player position
   player.savePlayer(write);
   write.flush();
   //write.newLine();
   //list of inventory and quantity
   write.write(String.valueOf(player.inventorySize()));
   write.flush();
   write.newLine();
   player.saveInventory(write);
   write.flush();
   //write.newLine();
   //write savefile count
   //write.write(count);
   write.flush();
   write.close();
   f.close();
   }
}
