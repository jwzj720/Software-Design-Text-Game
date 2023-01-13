package events;
import events.gameStateChange.gameStateChange;
/* Actions are choices the player can make. Actions at base contain flavor text but also may contain a limiter (something the player needs to initiate action)
 * and an outcome ( a change to game state resulting from the action) 
 */
public class action{
	public String flavor_text;
	// are limiters only items? 
	public String[] limiters;
	// gameStateChange can be one of 4 options:win,lose,add_inventory,remove_inventory 
	public gameStateChange gameStateChange; 
	}

