package events;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import WorldOrigin.*;
import BoardPack.Board;
import BoardPack.BoardI;

public class event{
	public char symbol;
	public String flavor_text;
	public ArrayList<action> actions;
	public WorldOrigin.player player;

	/**
	 * Method is called when a player hits a symbol on the board to trigger the corresponding event
	 * @param symbol the symbol on the board that the player lands on after a move 
	 * @param possible_events all the possible events that can be triggered on a board by the symbols
	 * @param player the current player of the game
	 * @param board the current board interface for the game
	 */
	public static BoardI execute_event(char symbol, ArrayList<event> possible_events,
							  player player, BoardI board) throws Exception{
		// Find the event that corresponds to the symbol
		event current_event = null;
		for(event e: possible_events){
			if (e.symbol == symbol){
				current_event = e;
				break;
			}
		}
		if(current_event == null) return null;
		int user_input = 0;
		// Loop only ends when the put in a valid answer
		action selected_action = new action();
		while(user_input == 0){
			present_actions(current_event);
			user_input = accept_actions(current_event);
			selected_action = current_event.actions.get(user_input-1);
			// Check if the inventory contains all limiters
			if(check_limiters(selected_action,player )) break; else user_input = 0;
		}
		// Execute game state change		
		selected_action.gameStateChange.execute(player, board);
		return board;
	}
	
	/**
	 * Prints the possible actions that a player can take after landing on a symbol
	 * @param e the event corresponding to the symbol the player has hit
	 */
	public static void present_actions(event e ){
		// Print out flavor text
		System.out.println(e.flavor_text + "\n Select an action:");
		// Print out actions
		for(int i = 0; i < e.actions.size();i++){
			System.out.println((i+1) + ") " + e.actions.get(i).flavor_text);
		}
	}
	
	/**
	 * Reads in the input of the player to pick which action to take
	 * @param e the current event that is happening
	 * @return user_input_as_int the integer that the player types that picks one of the actions
	 */
	public static int accept_actions(event e) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String user_input = reader.readLine();
		int user_input_as_int;
		// Check for incorrect input
		try{
			user_input_as_int = Integer.valueOf(user_input);
		}catch (Exception exception){
			System.out.println("Incorrect input. Please enter a valid number.");
			return 0;
		}
		if(user_input_as_int <= e.actions.size() && user_input_as_int > 0){
			return user_input_as_int;
		}else{
			System.out.println("Incorrect input. Please enter a valid number.");
			return 0;
		}
	} 

	/**
	 * Checks through a players inventory to see if it contains a certain item
	 * @param selected_action the action that the player picked
	 * @param player the current player
	 * @return boolean whether the item is in the inventory or not
	 */
	public static boolean check_limiters(action selected_action,player player){
		for(String limiter: selected_action.limiters){
			if(player.checkItemInInventory(limiter)) {
				return true;
			}else{
				System.out.println("You must have " + limiter + " for this action.");
				return false;
			}
		}
		return true; 
	}
}


