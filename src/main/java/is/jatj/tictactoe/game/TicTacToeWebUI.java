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
		get("/move/:param", new Route() {
        	public Object handle(Request request, Response response) {
                    int move = Integer.parseInt(request.params(":param"));
                    if(!gameController.isMoveLegal(move)) {
                    	return body("<p>do something</p>");
                    }
                    gameController.makeMove(move);
                    if(gameController.checkForWinner()) {
                    	return body("<p>do something</p>");
                    }
                    gameController.updatePlayer();
                    return body(displayBoard());
        	}
	});
	}
	public String body(String output)
	{
		 String head = "<!doctype html><html><head><style></style><title>JATJ - TicTactoe</title></head><body>";
		 String end = "</body></html>";
		 String body = head + output + end; 
		 return body;
	}
	public String displayBoard() {
		String boardOutput = "";
		char[][] board = gameController.getBoard();
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				boardOutput += "<a href='/move/" + i + "'>" + board[i][j] + "</a>";
			}
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
