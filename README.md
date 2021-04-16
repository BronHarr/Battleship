####Welcome to COP3252 BAttleship README####
Greg Garman
Jason Graham
Brian Hauer

----> Main Menu <--------
The game opens to the main menu. 
you can start a new game with the "new game" button
or read the instructions with the "instructions" button

Instructions:
"Game Instructions
At the start of the game each player will place their ships while the other looks away.
Once the ships are placed the players will take turns selecting on the grid to shoot.
(Ships will be hidden to both players) Winner is the first person to sink all enemy ships
"


------> Placing Ships <-------
a new Frame will open up, and Player 1 will be the first to place their ships. using
the buttons on the lefthand collumn, they may select any ship in any order, and place
them in any orientation that becomes shaded by the interface. when all ships are placed,
a ready button appears to move on. if dissatisfied, the player may also reset their ship positions
with the 'reset' button. the process is identical for player 2, only when they are 
finished the game begins.


------> Game <-------
Player one moves first, simply click on the cell you wish to fire at. the board will update
that cell to show a hit or a miss, and then proceed to the next turn. If ship sinks, the board
will notify the player which ship was sunk, and will also detect when no ships on either
side are left. once either player wins, they have the option to go back to the main menu
to start a new game


BattleShipFrame.Java:
implements a timer that periodically scans the state of the current board to be
able to tell how many turns have passed between them. if the player who moved first has taken
more turns, and currentTurn is currently set to them, the board determines it is time to change.

The boards themselves are JPanels that contain a 10x10 grid of custom JPanels that react to clicks
with anonymous listeners.

------><--------
-
-
Brian was responsible for BattleshipFrame.java, WaterPanel.java, and the player move classes
