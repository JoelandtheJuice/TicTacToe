package main.java.is.jatj.tictactoe.game;

import java.util.Scanner;

public class TicTacToe {
	private char[][] board;
	private  char[] player;
	private static int currentPlayer;
	
	private TicTacToe()
	{
		initializePlayer();
		initializeBoard();
	}
	private void initializePlayer()
	{
		player = new char[2];		
		player[0] = 'O';
		player[1] = 'X';
		currentPlayer = 0;
	}
	private void initializeBoard()
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
			System.out.println("Out of bounds, try again");
			return false;
		}
		else if(board[move/3][move%3] == 'X' || board[move/3][move%3] == 'O')
		{
			System.out.println("This move has already been made");
			return false;
		}
		return true;
	}
	private void makeMove(int move, int n)
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
	private void displayBoard()
	{

		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	};

 	public static int getInput()
 	{
 		Scanner reader = new Scanner(System.in);
 		int input = reader.nextInt() - 1;
 		return input;
 	}
 	
	public static void start(TicTacToe game)
	{
		while(true)
		{
			game.displayBoard();
			System.out.print("Player " + game.player[game.currentPlayer] + " select you're move: ");
			int move = getInput();
			
			game.makeMove(move, game.currentPlayer);
			// TODO: Check for win
			game.currentPlayer = (game.currentPlayer + 1) % 2;
			System.out.println();

		}
	}
	public static void main(String[] args)
	{
		TicTacToe game = new TicTacToe();
		while(true)
		{
			start(game);
			System.out.println("Do you want to play another game? y/n ");
			// TODO: Check if continue
			// TODO: score and switchplayer
		}
	}
}

