package WorldOrigin;
import BoardPack.*;
import WorldOrigin.WorldOriginExceptions.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;


public class player 
{
    private LocationI playerLoc;
    public ArrayList<itemI> inventory;

    public player(LocationI start)
    {
        playerLoc = start;
        inventory = new ArrayList<>();
    }
    /**
     * this is for the save game, when we need to put the player on the map. 
     * @param resume - where the player should resume
     * @param inventory - the existing player inventory
     */
    public player(LocationI resume, ArrayList<itemI> inventory)
    {
        playerLoc = resume;
        this.inventory = inventory;
    }

    public LocationI getPlayerLoc()
    {
        return playerLoc;
    }
    public void setPlayerLoc(LocationI loc)
    {
        playerLoc = loc;
    }
    public int inventorySize()
    {
        return inventory.size();
    }
    public void addInventory(String itemName)
    {   
        itemI tempItem = new item(itemName, 1);
        if(inventory.contains(tempItem))
        {
            int i = inventory.indexOf(tempItem);
            itemI additionItem = inventory.get(i);
            additionItem.setQuanity(additionItem.getQuanity() + 1);
        }
        else
        {
            inventory.add(tempItem);
        }
    }
    public void removeInventory(String itemName) throws Exception{
        itemI tempItem = new item(itemName, 1);
        if(inventory.contains(tempItem)) {
            if(tempItem.getQuanity() > 1)
            tempItem.setQuanity(tempItem.getQuanity()-1);
            else
                inventory.remove(tempItem);
        }else{
            throw new MissingItemException();
        }
    }

    public void addInventoryFromSave(item i){ 
        inventory.add(i);
    }

    public void saveInventory(BufferedWriter write) throws IOException
    {
        if(inventory.size() != 0)
        {
        for (itemI i : inventory) {
            
            write.write(i.toString());
        }
    }
    }
    public boolean checkItemInInventory(String item){
        for(itemI i: this.inventory){
          if(i.getName().equals(item)){
            return true;
          }
        }
        return false;
    }

    public void savePlayer(BufferedWriter write) throws IOException
    {
        write.write(playerLoc.toString()+"\n");
    }
    public ArrayList<itemI> getInventory()
    {
        return inventory;
    }
    /**
     * @Override
     * @param other
     * @return
     */
   public boolean equals(player other)
   {    
        if(this.playerLoc.getX() == other.getPlayerLoc().getX() && this.playerLoc.getY() == other.getPlayerLoc().getY())
        {
            if(this.inventory.equals(other.getInventory()))
            {
                return true;
            }
        }
        return false;
        
   }
}
