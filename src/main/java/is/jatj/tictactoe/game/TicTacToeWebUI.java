package main.java.is.jatj.tictactoe.game;

import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;

public class TicTacToeWebUI {
	TicTacToeController gameController;
	private int gameTurn;
	// obligatory comment
	
	public TicTacToeWebUI()
	{
		gameController = new TicTacToeController();
		gameTurn = 0;
	}
	public void start() {
		get("/", new Route() {
            public Object handle(Request request, Response response) {
                return body(displayBoard());
            }
        });
		
		get("/move/:param", new Route() {
        	public Object handle(Request request, Response response) {
        		if(gameTurn > 8) {
					gameController.startNewGame();
					gameTurn = 0;
					return body("<p> DRAW <a href='/'>Go back</a></p>");	
			    }
			    gameTurn++;
                    int move = Integer.parseInt(request.params(":param"));
                    if(!gameController.isMoveLegal(move)) {
                    	return body("<p>Illegal move <a href='/'>Go back</a></p>");
                    }
                    gameController.makeMove(move);
                    if(gameController.checkForWinner()) {
                    	char winner = gameController.getCurrentPlayerChar();
                    	gameController.startNewGame();
                    	gameTurn = 0;
                    	return body("<p>GJ " + winner + 
                    			" YOU WON <a href='/'>Go back for more</a></p>");
                    }
                    gameController.updatePlayer();
                    return body(displayBoard());
        	}
		});
	}
	// Assembles the body
	public String body(String output) {
		 String head = "<!doctype html><html>" + 
				 "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>" + 
				 "<head><style></style><title>JATJ - TicTacToe</title>" + 
				 "<div><div style='width: 50%; margin: 0 auto'></head><body>";
		 String gameName = "<h1>TicTactoe<h1>";
		 
		 String notification = "<h2>Your move " + gameController.getCurrentPlayerChar() +
				 " (Player " + gameController.getCurrentPlayer() + ")</h2>";
		 
		 String scoreTable = getScore();
		 String end = "</div></div></body></html>";
		 String body = head + gameName + output + notification + scoreTable; 
		 return body + end;
	}
	public String getScore() {
		String playerOne = "<p class='btn btn-info'>Player 1 has " + gameController.getPlayerScore(0) + " points</p></br>";
		String playerTwo = "<p class='btn btn-info'>Player 2 has " + gameController.getPlayerScore(1) + " points</p></br>";
		
		return playerOne + playerTwo;
	}
	public String displayBoard() {
		
		String css = "class='col-md-1'";
		String style = " style='border-style:solid'";
		String aStyle = "style='display:block;'";
		String boardOutput = "";
		int counter = 0;
		char[][] board = gameController.getBoard();
		for (int i = 0; i < 3; i++) {
			boardOutput += "<div class='row'>";
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 'X' || board[i][j] == 'O') {
					boardOutput += "<div " + css + style +"><a href='/move/" + counter + "'>" + 
				board[i][j] + "</a></div>";
				} else {
					boardOutput += "<div " + css + style +"><a " + aStyle + " href='/move/" + counter + "'>&nbsp;</a></div>";
				}
				counter++;
			}
			boardOutput += "</div>";
		}
		
		return boardOutput;
	}
	public static void main(String[] args) {
		TicTacToeWebUI gameUI = new TicTacToeWebUI();
		port(getHerokuAssignedPort());
        
        if (args.length == 0) {
            gameUI.start();
            try {
                String url = "http://127.0.0.1:" + getHerokuAssignedPort();
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (java.io.IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
           gameUI.start();
           }
		
	}
	
	static int getHerokuAssignedPort() {
	    ProcessBuilder processBuilder = new ProcessBuilder();
	    if (processBuilder.environment().get("PORT") != null) {
	    	return Integer.parseInt(processBuilder.environment().get("PORT"));
	    }
	return 5000; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}
