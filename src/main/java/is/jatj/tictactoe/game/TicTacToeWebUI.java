package main.java.is.jatj.tictactoe.game;

import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;

public class TicTacToeWebUI {
	TicTacToeController gameController;
	private int gameTurn;
	public int port;
	// obligatory comment
	
	public TicTacToeWebUI()
	{
		gameController = new TicTacToeController();
		port = getHerokuAssignedPort();
		gameTurn = 0;
	}
	public void start() {
		get("/", new Route() {
            public Object handle(Request request, Response response) {
                return body("", displayBoard());
            }
        });
		
		get("/move/:param", new Route() {
        	public Object handle(Request request, Response response) {
        		if(gameTurn > 7) {
					gameController.startNewGame();
					gameTurn = 0;
					return body("DRAW!", displayBoard());	
			    }
			    gameTurn++;
                    int move = Integer.parseInt(request.params(":param"));
                    if(!gameController.isMoveLegal(move)) {
                    	return body("That's an illegal move! Please try again.", displayBoard());
                    }
                    gameController.makeMove(move);
                    if(gameController.checkForWinner()) {
                        int winner = gameController.getCurrentPlayer();
                    	gameController.startNewGame();
                    	gameTurn = 0;
                    	return body("Player " + winner + " wins!", displayBoard());
                    }
                    gameController.updatePlayer();
                    return body("", displayBoard());
        	}
		});
	}
	// Assembles the body
	public String body(String endMessage, String board) {
         String head = "<html><head><link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'><style>*{border-radius: 0 !important;}.center {float: none;margin-left: 20%;margin-right: 20%;}.board {padding-top: 5%;padding-bottom:5%;}.center table {margin: 0 auto;text-align: left;}.square{background-color: #EBEBEB;width:12vw;height:12vw;}.square:hover {background-color: gray;}td a {border-color:#888;display:block;border-style: solid;border-width:1px;}td a p {vertical-align: middle;text-align:center;font-size: 12vw;width:12vw;height:12vw;margin-bottom:-10px;padding-bottom:10%;}td a:hover {text-decoration:none;}.alerthover:hover {border-color: rgba(240,240,240,0.7);background-color: rgba(240,240,240,0.7);text-decoration:none;}</style><title>JATJ - TicTacToe</title></head><body><div class='col-lg-12'>";
         String notification = "<div class='alert alert-info' role='alert'><p>Your move " + gameController.getCurrentPlayerChar() + "(Player " + gameController.getCurrentPlayer() + ")</p></div>";
		 String alert = "<div class='alert alert-warning alerthover' role='alert'><p>" + endMessage + "</p></div>";
         
		 String scoreTable = getScore();
         String end = "</div></div></body></html>";
         String body = head + board + alert + notification + scoreTable;
         if (endMessage == "") {
                body = head + board + notification + scoreTable; 
                return body + end;
         }
		 return body + end;
	}
	public String getScore() {
        String scoreHeader = "<div class='panel panel-default'><div class='panel-heading'><h3 class='panel-title'>Scores</h3></div><ul class='list-group'><li class='list-group-item'><span class='badge'>";
        
        String playerOne = gameController.getPlayerScore(0) + " points</span>Player 0</li><li class='list-group-item'><span class='badge'>";
        String playerTwo = gameController.getPlayerScore(1) + " points</span>Player 1</li></ul></div>";
		
		return scoreHeader + playerOne + playerTwo;
	}
	public String displayBoard() {
		
		String css = "class='col-md-1'";
		String style = " style='border-style:solid'";
		String aStyle = "style='display:block;'";
		String boardOutput = "<div class='col-md-6 center'><div class='board'><table>";
		int counter = 0;
		char[][] board = gameController.getBoard();
		for (int i = 0; i < 3; i++) {
			boardOutput += "<tr id='row'>";
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == 'X' || board[i][j] == 'O') {
                    boardOutput += "<td class='square'><a href='/move/" + counter + "' class='square'><p>" + board[i][j] + "</p></a></td>";
				} else {
                    boardOutput += "<td class='square'><a href='/move/" + counter + "' class='square'></a></td>";
				}
				counter++;
			}
            boardOutput += "</tr>"; 
		}
        boardOutput += "</table></div></div><div class='col-md-6 center'>";
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
	return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}
