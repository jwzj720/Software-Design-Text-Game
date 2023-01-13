package BoardPack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import WorldOrigin.*;
import events.event;

public class moveAction{
	
	static Map<Character, LocationI> obstacle_dict= new HashMap<Character, LocationI>(); 

	public static BoardI move(player player, Location loc, BoardI board, ArrayList<event> e) throws Exception{
		int x = loc.getX();
		int y = loc.getY();
		LocationI playerLoc = player.getPlayerLoc();
		LocationI obstacleLoc = new Location(playerLoc.getX()+x, playerLoc.getY()+y);

		Character value = (Character) board.get(playerLoc);
		Character obstacle = (Character)board.get(obstacleLoc);

		if (obstacle == '.'){

			for (Map.Entry<Character,LocationI> entry : obstacle_dict.entrySet()) {

				if (entry.getValue().getX() == playerLoc.getX() && entry.getValue().getY() == playerLoc.getY()){
					board.update(playerLoc, entry.getKey());
					player.setPlayerLoc(new Location(playerLoc.getX()+x, playerLoc.getY()+y));
            		board.update(player.getPlayerLoc(), value);
					board.draw();
					return board;
				}
			}
			board.update(playerLoc, obstacle);
			player.setPlayerLoc(new Location(playerLoc.getX()+x, playerLoc.getY()+y));
            board.update(player.getPlayerLoc(), value);
			board.draw();
		}
		else if(obstacle == '#'){
			System.out.println("You have hit a wall");
			board.draw();
		}
		else{
			System.out.println("You have hit something!");

			events.event.execute_event(obstacle.charValue(), e, player, board);
			playerLoc.setX(player.getPlayerLoc().getX()); 
			playerLoc.setY(player.getPlayerLoc().getY()); 
			obstacle_dict.put(obstacle, obstacleLoc);
			board.update(playerLoc, '.');
			player.setPlayerLoc(new Location(playerLoc.getX()+x, playerLoc.getY()+y));
			board.update(player.getPlayerLoc(), value);
			board.draw();
		
			}
		return board;
	}
		
} 
