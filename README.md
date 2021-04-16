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
Greg was responsible for PlaceShips.java/PlaceShipsPlayer2.java, Ship.java, retrieval and implementation of sound effects and background image.
Brian was responsible for BattleshipFrame.java, WaterPanel.java, and the player move classes


----> extra features <------
sound effects - sound files played in reaction to game events
Main menu - central menu to start the game from and return to when the game ends


---> Citing Resources <---

Main Menu theme sound: Free sound effect retrieved from https://mixkit.co/free-sound-effects/win/ and per the website policy, sound effects used for “educational purposes” qualify as usage under the “free license”, and items used under this license “can be used in your commercial and non-commercial projects for free.” This sound effect is property of the mentioned source and is not content belonging to any group members of this project.

Explosion sound used during gameplay: Free sound effect retrieved from https://mixkit.co/free-sound-effects/explosion/ and per the website policy, sound effects used for “educational purposes” qualify as usage under the “free license”, and items used under this license “can be used in your commercial and non-commercial projects for free.” This sound effect is property of the mentioned source and is not content belonging to any group members of this project.

Ship sink sound used during gameplay: Free sound effect retrieved from https://mixkit.co/free-sound-effects/explosion/ and per the website policy, sound effects used for “educational purposes” qualify as usage under the “free license”, and items used under this license “can be used in your commercial and non-commercial projects for free.” This sound effect is property of the mentioned source and is not content belonging to any group members of this project.

Water splashing sound: Free sound effect retrieved from https://www.fesliyanstudios.com/royalty-free-sound-effects-download/water-splashing-20 and the website specifies “These free water splashing sound effects can be downloaded and used for video editing, adobe premiere, foley, youtube videos, plays, video games and more!” and the site requests that the following line be included to credit them- “free sound effects from https://www.fesliyanstudios.com” This sound effect is property of the mentioned source and is not content belonging to any group members of this project.

Player winning sound effect: Free sound effect retrieved from https://mixkit.co/free-sound-effects/win/ and per the website policy, sound effects used for “educational purposes” qualify as usage under the “free license”, and items used under this license “can be used in your commercial and non-commercial projects for free.” This sound effect is property of the mentioned source and is not content belonging to any group members of this project.

Sound effect when pressing “Ready” button: Free sound effect retrieved from https://mixkit.co/free-sound-effects/win/ and per the website policy, sound effects used for “educational purposes” qualify as usage under the “free license”, and items used under this license “can be used in your commercial and non-commercial projects for free.” This sound effect is property of the mentioned source and is not content belonging to any group members of this project.

Background photo used in main screen: This image was obtained from https://stock.adobe.com/  after creating a 7-day free trial for the “standard license” and according to website policy, those who have the standard license may “reproduce up to 500,000 copies of the asset in all media, including product packaging, printed marketing materials, digital documents, or software.“ This image is property of the mentioned source and is not content belonging to any group members of this project.

