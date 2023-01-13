package saveGame;

import java.io.*;
import java.util.ArrayList;

import BoardPack.*;
import WorldOrigin.*;
import boardExceptions.*;

public class load {
    private BufferedReader br;
    private File loadFile;
    private int count;
    /**
     * load takes a save file and creates a game
     * @param f  the save file
     * @param count  the count of saves (NOT IMPLEMENTED)
     * @throws FileNotFoundException  if save file doesn't exist
     */
    public load(File f, int count) throws FileNotFoundException
    {
        this.loadFile = f;
        br = new BufferedReader(new FileReader(loadFile));
        this.count = count;
    }
   
    /**
     * loads the board/map
     * @return  the board from the save file
     * @throws Exception  if the save file is corrupted throws a boardException or if BufferedReader{@link BufferedReader} can't read the line
     */
    public BoardI loadBoard() throws Exception
    {
        try {
            // Open the file for reading
            // Read the dimensions line
            String line = br.readLine();
            System.out.println(line);
            String[] dims = line.split("\\s+");
            int width;
            int height;
            width = Integer.parseInt(dims[0]);
            height = Integer.parseInt(dims[1]);
            // Make the board from the board lines
            BoardI<Character> b = new Board<Character>(height, width);
            for (int y = 0; y < height; y++) {
                line = br.readLine();
                for (int x = 0; x < width; x++) {
                    b.update(new Location(x, y), line.charAt(x));
                }
            }
            return b;
        } catch (FileNotFoundException e) {
            throw new MissingFileException();
        }
    }
    /**
     * loads the player data from save file
     * @return  the player with inventory
     * @throws IOException if BufferedReader{@link BufferedReader} can't read the line
     */
    public player loadPlayer() throws IOException
    {
        String line = br.readLine();
        String[] dims = line.split("\\s+");
            int x;
            int y;
            x = Integer.parseInt(dims[0]);
            y = Integer.parseInt(dims[1]);
        player p = new player(new Location(x,y));
       // read size
        line = br.readLine();
        int size;
        size = Integer.parseInt(line);
        //reads inventory
        for(int i = 0; i < size; i++)
        {
            line = br.readLine();
            String[] sims = line.split("\\s+");
            int quanity;
            quanity = Integer.parseInt(sims[1]);
            p.addInventoryFromSave(new item(sims[0], quanity));
        }
        return p;
    }
    /**
     * keeps track of the number of saves thus far for naming
     * #TODO finish implementation of feature
     * @throws IOException if BufferedReader{@link BufferedReader} can't read the line
     */
    public void count() throws IOException
    {
        String line = br.readLine();
        //reads count
        count = Integer.parseInt(line);
        br.close();

    }

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public File getLoadFile() {
        return loadFile;
    }

    public void setLoadFile(File loadFile) {
        this.loadFile = loadFile;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}