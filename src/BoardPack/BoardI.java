package BoardPack;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/** Represents a 2D board for TAP
 *
 */
public interface BoardI<T> {

    /** Updates the board
     *
     */
    public void update(LocationI l, T thing);

    public T get(LocationI l);

    /** Draws the board 
     *
     */
    public void draw();
    public void saveGame(BufferedWriter file) throws IOException;
    public int getNumRows();
    public int getNumCols();

    public ArrayList<T> getData();
}
