package main.java.is.jatj.tictactoe.game;

import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;
public class TicTacToeWebUI {
	TicTacToeController gameController;
	// obligatory comment
	public TicTacToeWebUI()
	{
		gameController = new TicTacToeController();
	}
	public void start() {
		displayBoard();
		
		get("/", new Route() {
            public Object handle(Request request, Response response) {
                return body(displayBoard());
            }
        });
		
		get("/move/:param", new Route() {
        	public Object handle(Request request, Response response) {
                    int move = Integer.parseInt(request.params(":param"));
                    if(!gameController.isMoveLegal(move)) {
                    	return body("<p>Illegal move</p>");
                    }
                    gameController.makeMove(move);
                    if(gameController.checkForWinner()) {
                    	return body("<p>GJ " + gameController.getCurrentPlayerChar() + " YOU WON!</p>");
                    }
                    gameController.updatePlayer();
                    return body(displayBoard());
        	}
		});
	}
	public String body(String output)
	{
		 String head = "<!doctype html><html>" + 
				 "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>" + 
				 "<head><style></style><title>JATJ - TicTactoe</title></head><body>";
		 String gameName = "<h1>TicTactoe<h1>";
		 String end = "</body></html>";
		 String body = head + gameName + output + end; 
		 return body;
	}
	public String displayBoard() {
		
		String css = "col-md-1 btn-group btn-group-justified";
		String boardOutput = "";
		int counter = 0;
		char[][] board = gameController.getBoard();
		for (int i = 0; i < 3; i++) {
			boardOutput += "<div class='row'>";
			for(int j = 0; j < 3; j++) {
				boardOutput += "<div class=" + css + "><a href='/move/" + counter + "'>" + board[i][j] + "</a></div>";
				counter++;
			}
			boardOutput += "</div>";
		}
		
		return boardOutput;
	}
	public static void main(String[] args) {
		TicTacToeWebUI gameUI = new TicTacToeWebUI();
		port(getHerokuAssignedPort());
	    
		gameUI.start();
		
	}
	
	static int getHerokuAssignedPort() {
	    ProcessBuilder processBuilder = new ProcessBuilder();
	    if (processBuilder.environment().get("PORT") != null) {
	    	return Integer.parseInt(processBuilder.environment().get("PORT"));
	    }
	return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}
