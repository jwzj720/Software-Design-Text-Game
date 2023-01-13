package BoardPack;
import boardExceptions.*;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
// Reads in a map file and parses it into something readable by the gameEngine
public class parseBoard {
     /**
     * Read in a board file
     *
     * @param mapfile the file to try to read
     * @return the Model.BoardI instance if the file was read correctly
     */
    public static BoardI<Character> readBoard(File mapfile) {
        BoardI<Character> b = null;
        try {
            // Open the file for reading
            if (mapfile == null) throw new MissingFileException();
           
            BufferedReader br = new BufferedReader(new FileReader(mapfile));
            // Read the dimensions line
            String line = br.readLine();
            String[] dims = line.split("\\s+");
            

            if (dims.length != 2) throw new BadDimensionalityException();
            int width;
            int height;
            try {
                width = Integer.parseInt(dims[0]);
                height = Integer.parseInt(dims[1]);
            } catch (NumberFormatException e) {
                throw new BadDimensionValuesException();
            }
            if (width <= 0 || height <= 0) {
                throw new BadDimensionValuesException();
            }
            // Make the board from the board lines
            b = new Board<Character>(height, width);
                for (int y = 0; y < height; y++) {
                    line = br.readLine();
                    if(line.length() == 0) throw new UnsupportedBlankLinesException();
                    else if(line.length() != width) throw new XDimensionDiscrepencyException();
                    for (int x = 0; x < width; x++) {
                        
                        b.update(new Location(x, y), line.charAt(x));   
                        
                    }
                }
            br.close();
            
        } catch (Exception e) {
            e.getMessage();
            System.exit(1);
        }
        
        return b;
    }  
	
}
