package main.java.is.jatj.tictactoe.game;

import java.util.Scanner;

public final class TicTacToe {
	static TicTacToeController game;
	
	private TicTacToe()
	{
		game = new TicTacToeController();
	}

 	public static int getInput()
 	{
 		Scanner reader = new Scanner(System.in);
 		int input = reader.nextInt() - 1;
 		return input;
 	}
	private static void start()
	{
		while(true)
		{
			displayBoard();
			System.out.print("Player " + game.getCurrentPlayerChar() + " select you're move: ");
			int move = getInput();
			
			game.makeMove(move);
			// TODO: Check for win
			System.out.println();

		}
	}
	private static void displayBoard()
	{
		char[][] board = game.getBoard();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	};

	public static void main(String[] args)
	{
		TicTacToe view = new TicTacToe();
		while(true)
		{
			view.start();
			System.out.println("Do you want to play another game? y/n ");
			// TODO: Check if continue
			// TODO: score and switchplayer
		}
	}
}
