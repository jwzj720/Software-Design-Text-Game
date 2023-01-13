### saveGame
This folder holds the files that make up the saving and loading logic. The saving and loading fuctionality interacts with the board, the player, and hold metadata. 
## save.java
This file holds the logic to save the game. It generates a file with the naming convention TAP + the number of saves.txt. It is formatted as:
rows cols
BOARD
player loc.
number of inventory items
list of items in inventory

example:

10 5
##########
#..A.....#
#..B.....#
#..C.....#
##########
3 2
1
test 1
