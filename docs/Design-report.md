#TicTacToe - Design report#

----------

##Team - Joel and the juice##

* Bjarni Maron Sigurðsson
* Jónas Arnþór Guðmundsson
* Kristján Jóel Kristjánsson
* Leifur Viktor Guðmundsson
* Stefán Valentínusson


----------

##Introduction##
The following document is a design report for a assignment in Hugbúnaðarfræði. We will be going over how we planned on implementing a TicTacToe game using Test-driven development (TDD)  using Github and a as many various other tools as we could, such as Travis, Heroku and Selenium for either testing or deployment.

----------

##Design##
To begin with we implemented a console version of the game. Initial design was to follow the MVC architectural pattern in case we would like to implement a web-ui in the most easiest way possible.

To begin with we had figure out the basic logic of the game and how we were going to implement that. The following list describe the core elements we needed to implement.


* **The board**
	* A 3x3 square stored in a two dimensional array
	* 1 2 3
    * 4 5 6
    * 7 8 9
* **Players**
	* Two players, O and X
	* Array to keep track of score
	* Players can be both X or O, depending on game
	* Player makes a move
	* Take a command from keyboard/mouse and change the board array
	* Check if that move is valid
	* Check if player won the game or if it’s a draw
* **The user interface**
	* Display the board
	* Ask for input / get input
	* Give input to controller and make the move
* **webUI (later)**
	* Run in a browser
	* Display all necessary information
	* Handle requests / response

----------

##Implementation and testing##

We gathered all the elements needed for the game to work and created tests to test our implementation. Each addition to our project was then pushed to our Git repository followed by a build on Travis. Depending on the result of the build, we either fixed our code or moved to the next step. In the beginning we didn’t deploy to Heroku, it wasn’t until we had finished all of our 12 unit tests that we started working on a web-ui capable of being hosted on a server like Heroku. This transition proved to be very easy since we already had the controller and repo classes ready and only made changes to the view. 















##Class diagram##
The class diagram below is the describes the latest version of the console version. 

![diagram1](docs/diagram1)

![diagram2](docs/diagam2)

This class diagram shows the current version of the web-ui implementation. It might not be obvious but the controller and repo classes are exactly the same. 

##Code coverage##

We are using the *Maven-checkstyle-plugin* to go over the code to check if it follows the *Sun coding conventions*.


