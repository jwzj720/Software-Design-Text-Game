package randomMapGeneration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class randomMapGenerator {
    /**
     * Creates a new file
     * @param filename name of the file
     * @return fileWriter object to edit the file
     * @throws IOException
     */
    public static FileWriter createNewFile(String filename) throws IOException{
        File f = new File(filename);
        try{
            f.createNewFile();
        }catch(IOException e){
            System.out.println("The specified path does not exists");
            System.exit(-1);
        }
        FileWriter fileWriter = new FileWriter(filename);
        return fileWriter;        
    }
    /**
     * Randomly adds in different symbols or "events" to the map, not on the edges tho
     * @param numEvents total number of events
     * @param mapData list containing all the chars on the map
     * @param cols number of cols in map
     * @return edited mapData
     */
    public static ArrayList<Character> addInEvents(int numEvents, ArrayList<Character> mapData,int cols){
        Random random = new Random();
        String alphabet = "!@#######$%^&*AQWERTYUIOPSDFGHJKLZXCVBNM~";
        int locationToChange = random.nextInt(mapData.size()-1);
        // Check if location to change is on the border of the board, Na'ama wrote this algorithim she is smart
        for (int i = 0; i < numEvents; i++) {
            locationToChange = random.nextInt(mapData.size()-1);
            while(locationToChange==-2%cols || locationToChange==0%cols || locationToChange<cols || locationToChange>mapData.size()-cols){
                locationToChange = random.nextInt(mapData.size()-1);
            }
            char eventChar = alphabet.charAt(random.nextInt(alphabet.length()));
            mapData.set(locationToChange,eventChar);
        }
        return mapData;
    }
    /**
     * Generates a blank board with no events
     * @param rows 
     * @param cols
     * @return mapData
     */
    public static ArrayList<Character> generateBlankMap(int rows, int cols){
        ArrayList<Character> mapData = new ArrayList<Character>();
        for(int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++){
                if(j==0)
                    mapData.add('#');
                else if(j==(cols-1))
                    mapData.add('#');
                else if(i==0)
                    mapData.add('#');
                else if(i==(rows-1))
                    mapData.add('#');
                else
                    mapData.add('.');
            }
        return mapData;
    }
    /**
     * Converts the list of characters on the map to a readable file
     * @param mapData list of characters in the map
     * @param fileWriter object to write to file
     * @param rows of board
     * @param cols of board
     */
    public static void convertMapDataToFile(ArrayList<Character> mapData,FileWriter fileWriter,
                                            int rows, int cols) throws IOException{
        for(int i=0;i<rows*cols;i++){
            // Every new row write a \n except for the last row
            fileWriter.write(mapData.get(i));
            if((i+1)%cols==0 && i!=(rows*cols)-1 && i!=0)
                fileWriter.write("\n");   
        }
        fileWriter.close();
    }
    public static void main(String[] args) throws Exception{
       
        int rows = 0;
        int cols = 0;
        int numOfEvents = 0;
        System.out.println("Generating a new random map\nHow many rows should it have?\n");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // Checking for correct inputs
        try{
            rows = Integer.valueOf(in.readLine());
            if(rows < 4){
                System.out.println("Must have four or more rows");
                System.exit(-1);
            }
        }catch (Exception e){
            System.out.println("Must be a valid integer");
            System.exit(-1);
        }
        System.out.println("How many columns\n");
        try{
            cols = Integer.valueOf(in.readLine());
            if(cols < 4){
                System.out.println("Must have four or more cols");
                System.exit(-1);
            }
        }catch (Exception e){
            System.out.println("Must be a valid integer");
            System.exit(-1);
        }
        System.out.println("How many events should there be on the map?\n");
        try{
            numOfEvents = Integer.valueOf(in.readLine());
            if(numOfEvents>((rows+cols)*2)-5){  // numEvents cannot exceed rows*cols * 2 - 5
                System.out.println("Must be a valid integer");
                System.exit(-1);
            }
        }catch (Exception e){
            System.out.println("Must be a valid integer");
            System.exit(-1);
        }
        System.out.println("What is the path of the file you want to create");
        String filename = in.readLine();       
        FileWriter fileWriter = createNewFile(filename);
        ArrayList<Character> mapData = generateBlankMap(rows, cols);
        mapData = addInEvents(numOfEvents, mapData, cols);       
        convertMapDataToFile(mapData,fileWriter,rows,cols);
    }
}
