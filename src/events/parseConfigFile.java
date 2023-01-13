package events;
import java.io.*;
import java.util.*;

import events.eventsExceptions.InvalidConfigActionException;
import events.eventsExceptions.InvalidConfigException;
import events.eventsExceptions.InvalidConfigSymbolException;
import events.eventsExceptions.InvalidConfigTextException;
import events.eventsExceptions.InvalidGameStateChangeException;
import events.eventsExceptions.InvalidMoveException;
import events.eventsExceptions.NoItemListedException;
import events.gameStateChange.addItem;
import events.gameStateChange.gameStateChange;
import events.gameStateChange.loseState;
import events.gameStateChange.movePlayer;
import events.gameStateChange.nullState;
import events.gameStateChange.removeItem;
import events.gameStateChange.winState;

public class parseConfigFile{
	/**
	 * Reads from the Config file
	 * @param filename Name of the config file, usually config.txt
	 * @return lines ArrayList of strings containing each line of the file
	 */
	public static ArrayList<String> readConfigFile(String filename){
		BufferedReader reader;
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line!=null) {
				lines.add(line);	
				line = reader.readLine();
			}
			reader.close();
			
		} catch(IOException e) {
			System.out.println("ERROR: could not read config file");
			System.exit(-1);
		}
		return lines;
	}
	/**
	* Creates all new events by reading all the information entered in the config file
	* @param lines correspond to one symbol's description separated by astriks
	* @return definedEvents all the symbols and corresponding events entered by the user
	*/
	public static ArrayList<event> parseConfigLines(ArrayList<String> lines) throws Exception{
		// TODO need execeptions if their config file is wrong
		ArrayList<event> definedEvents = new ArrayList<event>();
		for (int linenum = 0; linenum < lines.size();linenum++){
			// parsing a new event
			if (lines.get(linenum).equals("***")){
				event new_event = new event();
				// Symbol Line 
				new_event.symbol = getSymbolLine(lines,linenum);
				// Text Lines
				try{
					new_event.flavor_text = getTextLine(lines, linenum);
				}catch(Exception e){
					e.printStackTrace();
					throw new InvalidConfigTextException();
				}
				// Action Lines
				try{
					new_event.actions = getActionLines(lines, linenum);
				}catch (Exception e){
					e.printStackTrace();
					throw new InvalidConfigActionException();
				}
				definedEvents.add(new_event);
			}
		}
		return definedEvents;
	}
	/**
	 * Parses the line that contains the symbol value 
	 * @param lines ArrayList of strings containing each line of the file
	 * @param linenum int correspoding to the index in lines the current line is on 
	 * @return symbol the symbol value for the line
	 */
	public static char getSymbolLine(ArrayList<String> lines,int linenum) throws Exception{
		String symbol_line = lines.get(linenum+1);
		// If length is not 8, the symbol is not a char
		if(symbol_line.length() != 8)
			throw new InvalidConfigSymbolException();
		char symbol =  symbol_line.charAt(symbol_line.length()-1);
		return symbol;
	}
	/**
	 * Parses the line that contains the text value 
	 * @param lines ArrayList of strings containing each line of the file
	 * @param linenum int correspoding to the index in lines the current line is on 
	 * @return text the text value for the line
	 */
	public static String getTextLine(ArrayList<String> lines,int linenum){
		String text_line =  lines.get(linenum+2);
		// index 5 is the "="
		String text = text_line.substring(5,text_line.length());
		return text;	
	}
	/**
	* @param lines the input lines corresponding to actions
	* @param linenum int that iterates through the action lines
	* @return actions list of actions corresponding to one symbol
	*/
	public static ArrayList<action> getActionLines(ArrayList<String> lines,int linenum) throws Exception{
		ArrayList<action> actions = new ArrayList<action>();
		int line_index = 4;
		String action_line = lines.get(linenum+line_index);
		
		while(!action_line.equals("***")){
			// create a new action for each line
			action new_action = new action();
			// Converting to a linkedList so that we can use the .remove() method
			List<String> line_list = new LinkedList<String>(Arrays.asList(action_line.split(":")));
			//Check for invalid format, should have at least 1 ":"
			if(line_list.size()<1)
				throw new InvalidConfigActionException();
			// Flavor text
			new_action.flavor_text = line_list.get(0);
			line_list.remove(0);
			// Adding the gameStateChange
			String gameStateChangeString = line_list.get(line_list.size()-1);
			new_action.gameStateChange = parseGameStateChangeString(gameStateChangeString);
			line_list.remove(line_list.size()-1);
			// Limiters
			// Can't cast line_list to array so we need to copy each element over
			String[] limiters = new String[line_list.size()];
			for(int i = 0; i < line_list.size(); i++){
				limiters[i] = line_list.get(i);
			}
			new_action.limiters = limiters;
	
			actions.add(new_action);
			// iterate to action
			line_index++;
			try{
				action_line = lines.get(linenum+line_index);
			} catch(IndexOutOfBoundsException e){
				// If it is the last action in the file this exception will trigger
				return actions;
			}
		}
		return actions;
	}
	/**
	 * Takes in a string version of the gameStateChange, parses some info about it, returns a gameStateChange Object
	 * @param gameStateChangeString string representation of the gameStateChange, ie "add_item[key]"
	 * @return gameStateChange game state change correspoding to the string
	 */
	private static gameStateChange parseGameStateChangeString(String gameStateChangeString) throws Exception{
		// Check for null at outset
		if(gameStateChangeString.equals("null"))
			return new nullState();
		// Need to seperate game state changes into the change and the additional info
		String[] splitGSC = gameStateChangeString.split("\\[");
		String stateChange = splitGSC[0];
		gameStateChange GSC = null;
		if(stateChange.equals("lose"))
			GSC = new loseState();
		else if (stateChange.equals("win"))
			GSC = new winState();
		else if(stateChange.equals("add_item")){
			if(splitGSC.length < 2) // Check for no item given
				throw new NoItemListedException();
			String item = splitGSC[1].substring(0,splitGSC[1].length()-1);
			GSC =  new addItem(item);
		}
		else if(stateChange.equals("remove_item")){
			if(splitGSC.length < 2) // Check for no item given
				throw new NoItemListedException();
			String item = splitGSC[1].substring(0,splitGSC[1].length()-1);
			GSC = new removeItem(item);
		}
		else if(stateChange.equals("move")){
			try{
				// Sorry for the long line
				String[] coordinateString = (splitGSC[1].substring(0,splitGSC[1].length()-1)).split(",");	
				int[] coordinates = new int[2];
				for(int i = 0; i < coordinates.length;i++)
					coordinates[i]=Integer.valueOf(coordinateString[i]);
				GSC = new movePlayer(coordinates);
			}catch (IndexOutOfBoundsException | NumberFormatException e){
				throw new InvalidMoveException();
			}
		}
		else throw new InvalidGameStateChangeException(stateChange);
		return GSC;
	}

}
