# Project Overview:
This is a text-based adventure game created by Walt Jones, James Settles, Na'ama Nevo, and Simay Curray for Colorado College's Software Design class. 
Walt Jones worked on packages: BoardPack, boardExceptions, config, saveGame, tests
# Formatting the Configuration File
The Configuration file contains all of the possible symbols that can appear on the map with the corresponding event.
The beginning of a description of a new symbol is indicated by a line with three astriks "***". The information should come in the following order after the astriks:

1. Indicate which symbol triggers this event
    - "symbol=" and the character without adding spaces
2. Write the text that the player will see when they hit this symbol on the map
    - "text=" and the text without adding a space after the "="
3. A line that indicates that actions are listed next
    - "actions:"
4. Write each possible action that a player can take in a separate line. There must be at least one action for any symbol. Separate the information by colons, beginning with the text the player will see describing the action, any limiting items the player must have in their inventory in order to carry out the action, and the game state change. There can be 0+ limiters, but there must be text and a gameStateChange.
    - "flavorText:limiter1:limiter2:gameStateChange"

## Game State Changes
There are five possible game state changes that correspond to an action.
1. Win: the player wins the game.
    - format in the action line: "win"
2. Lose: the player loses the game
    - format in the action line: "lose"
3. Add item: the player adds an item to their inventory
    - format in the action line: "add_item[name of item being added]"
4. Remove item: the player removes an item from their inventory
    - format in the action line: "remove_item[name of item being removed]"
5. Null: nothing changes
   - format in the action line: "null"
6. Move: the player moves by a given amount
    - format in the action line: "move[horizontal coordinate,vertical coordinate]"

Every action must have exactly one of these game state changes.

## Example Configuration File
```
***
symbol=D
text=Hello I am a door
actions:
Open door:key:win
leave:null
***
symbol=K
text=Hello I am a key
actions:
Pick up key:add_item[key]
leave:null
```

# Formatting the Map File

## CONTENTS OF THIS FILE
—------------------------------------
*Introduction
*Existing Classes
*Example Board File
*Configuration


## INTRODUCTION
—------------------------------------
The BoardPack file is in charge of generating and updating the map. Generating encompases parsing through the existing map file 
that the user provides. The classes parseBoard, Board, and Location are used for this. Updating encompases the player moving 
through the board and re-generating the map as it changes. The class moveAction.java is used for this.

## EXISTING CLASSES
—------------------------------------
-BoardI.java: The interface for Board.java
-Board.java: Takes in number of columns and rows wanted to make the board and makes a map consisting of 'null'. Has an update method to add/remove an object from a location. Has a draw method to print current board. Has a saveGame method to save current board in save
file.
-LocationI.java: The interface for Location.java.
-Location.java: Estantiates and returns the x and y integers of given coordinate.
-moveAction.java: Takes in the player, current board, event, and the coordinates that will be added to current player location. If the player destination is a path, the player is moved. If destination is an obstacle, an event is called and obstacle disappears.
-parseBoard.java: Takes in the map file user provides, reads through the file, and calls Board to generate map.

## EXAMPLE BOARD FILE
—------------------------------------
The example board file should include the number of columns and rows at the start of the file. After adding the desired size of
the map, the user should include what they want the map to look like. One restraint when making the map is the walls should be given the '#' symbol, and the paths '.'. Other than these two restraints the user can add whichever symbol to wherever they want in the map. 

Example files include:

```
10 5
##########
#..A.....#
#..B.....#
#..C.....#
##########
```

or

```
40 16
########################################
#................................TTT...#
#...............######..........T..T...#
#...............#..X.#..........T..T...#
#...............#....#.................#
#...............###A##.................#
#......................................#
#....TTTTT.............................#
#......................................#
#.................................###..#
#..@..............................#C#..#
#......................................#
##########.............................#
#......................................#
#...B..................................#
########################################
```