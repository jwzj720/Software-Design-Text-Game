package BoardPack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;



public class Board<T> implements BoardI<T> {
    private int numRows;
    private int numCols;
    private ArrayList<T> data;
    /**
     * Board class is inspired by the board from AlexGame in CS2.
     * Some code is taken from the local project on @Walt's local machine.
     * Board stores the map as a arraylist, and uses math to access each location.
     * @param numRows  the maps rows
     * @param numCols  the map cols
     */
    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.data = new ArrayList<T>(numRows*numCols);
        for(int i = 0; i < numRows*numCols; i++) {
            data.add(null);
        }

    }

    
    /**
     * @Override
     * update sets the value of a location
     * 
     * 
     * @param l  is the location
     * @param thing  is the thing to update
     */
    public void update(LocationI l, T thing) {
        data.set(this.numCols * l.getY() + l.getX(), thing);
    }

   
    /**
     * @Override
     * 
     * get gets the data at a location
     *
     * @param l  location to get data from
     * @return  the thing at the location
     */
    public T get(LocationI l) {
        return data.get(this.numCols * l.getY() + l.getX());
    }

    /**
     * @Override
     * 
     * draws the board in the CLI
     */
    public void draw() {
        for(int y = 0; y < this.numRows; y++) {
            for(int x = 0; x < this.numCols; x++) {
                T value = get(new Location(x,y));
                if(value == null) {
                    System.out.print('.');
                } else {
                    System.out.print(value);
                }
            }

            System.out.println();
        }

    }
    /**
     * saveGame writes the board to a file
     * 
     * @param file  file is a BufferedWriter {@link BufferedWriter} that 
     * writes to the save file.
     * @throws IOException  if file is unreadable
     */
    public void saveGame(BufferedWriter file) throws IOException
    {
        file.write(this.numCols + " " + this.numRows);
        file.newLine();
        for(int y = 0; y < this.numRows; y++) {
            for(int x = 0; x < this.numCols; x++) {
                T value = get(new Location(x,y));
                if(value == null) {
                    file.write(".");
                } else {
                    file.write(value.toString());
                }
            }
            file.newLine();
        }
    }

    /**
     * Returns the number of rows
     * @return number of rows
     */
    @Override
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * Returns the number of cols
     * @return number of col
     */
    @Override
    public int getNumCols() {
        return this.numCols;
    }
    public ArrayList<T> getData()
    {
        return data;
    }
}