package main.java.is.jatj.tictactoe.game;

public class TicTacToeController {
	TicTacToeRepo game;
	private static int currentPlayer;
	
	TicTacToeController()
	{
		game = new TicTacToeRepo();
		currentPlayer = 0;
	}
	
	public char[][] getBoard()
	{
		return game.getBoard();
	}
	
	public char getCurrentPlayerChar()
	{
		return game.getPlayer(currentPlayer);
	}
	
	public void updatePlayer()
	{
		currentPlayer = (currentPlayer + 1) % 2;
	}
	public boolean isMoveLegal(int move)
	{
		if(move < 0 || move > 8)
		{
			System.out.println("Out of bounds, try again");
			return false;
		}
		else if(game.board[move/3][move%3] == 'X' || game.board[move/3][move%3] == 'O')
		{
			System.out.println("This move has already been made");
			return false;
		}
		return true;
	}
	public void makeMove(int move)
	{
		game.board[move/3][move%3] = getCurrentPlayerChar();
		updatePlayer();
	}
	public boolean checkForWinner()
	{
		boolean winnerFlag = false;
		boolean rightAngle = true;
		boolean leftAngle = true;
		char currentChar = getCurrentPlayerChar();
		for(int i = 0; i < 3; i++)
		{
			boolean vertical = true;
			boolean horizontal = true;
			for(int j = 0; j < 3; j++)
			{
				if(game.board[i][j] != currentChar)
				{
					vertical = false;
				}
				if(game.board[j][i] != currentChar)
				{
					horizontal = false;
				}
				
			}
			if(vertical || horizontal)
			{
				winnerFlag = true;
				break;
			}
			
			if(game.board[i][i] != currentChar)
			{
				rightAngle = false;
			}
			if(game.board[i][2-i] != currentChar)
			{
				leftAngle = false;
			}
			
		}
		if(rightAngle || leftAngle)
		{
			winnerFlag = true;
		}
		return winnerFlag;
	}
}
