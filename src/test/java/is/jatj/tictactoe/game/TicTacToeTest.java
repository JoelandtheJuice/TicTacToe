package test.java.is.jatj.tictactoe.game;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TicTacToeTest {
	public char[][] board;
	public char[] player;
	public int currentPlayer;
	
	public TicTacToeTest()
	{
		initializePlayer();
		initializeBoard();
	}
	public void initializePlayer()
	{
		player = new char[2];		
		player[0] = 'O';
		player[1] = 'X';
		currentPlayer = 0;
	}
	public void initializeBoard()
	{
		board = new char[3][3];
		int counter = 1;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				board[i][j] = (char) ('0' + counter);
				counter++;
			}
		}
	}
	public boolean isMoveLegal(int move)
	{
		if(move < 0 || move > 8)
		{
			StdOut.println("Out of bounds, try again");
			return false;
		}
		else if(board[move/3][move%3] == 'X' || board[move/3][move%3] == 'O')
		{
			StdOut.println("This move has already been made");
			return false;
		}
		return true;
	}
	public void makeMove(int move, int n)
	{
		board[move/3][move%3] = player[n];
		
	}
	public boolean checkForWinner()
	{
		boolean winnerFlag = false;
		boolean rightAngle = true;
		boolean leftAngle = true;
		
		for(int i = 0; i < 3; i++)
		{
			boolean vertical = true;
			boolean horizontal = true;
			for(int j = 0; j < 3; j++)
			{
				if(board[i][j] != player[currentPlayer])
				{
					vertical = false;
				}
				if(board[j][i] != player[currentPlayer])
				{
					horizontal = false;
				}
				
			}
			if(vertical || horizontal)
			{
				winnerFlag = true;
				break;
			}
			
			if(board[i][i] != player[currentPlayer])
			{
				rightAngle = false;
			}
			if(board[i][2-i] != player[currentPlayer])
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
	public void displayBoard()
	{
		StdOut.println();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				StdOut.print(board[i][j] + " ");
			}
			StdOut.println();
		}
	};

 	public int getInput()
	{
		In in = new In();
		
		String move = in.readString();
		assert move.isNumber();
			
	}
	public static void start(TicTacToeTest game)
	{
		while(true)
		{
			StdOut.print("Player " + game.player[game.currentPlayer] + " select you're move: ");
			int move = getInput();

			game.makeMove(move, game.currentPlayer);
			game.displayBoard();
			// TODO: Check for win
			game.currentPlayer = (game.currentPlayer + 1) % 2;
			StdOut.println();
		}
	}
	public static void main(String[] args)
	{
		In in = new In();
		TicTacToeTest game = new TicTacToeTest();
		while(true)
		{
			start(game);
			StdOut.println("Do you want to play another game? y/n ");
			// TODO: Check if continue
			// TODO: score and switchplayer
		}
	}
}

