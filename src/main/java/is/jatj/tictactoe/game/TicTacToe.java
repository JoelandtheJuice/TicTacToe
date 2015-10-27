package main.java.is.jatj.tictactoe.game;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TicTacToe {
	protected char[][] board;
	private  char[] player;
	public int currentPlayer;
	public TicTacToe()
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
	private void makeMove(int move, int n)
	{
		board[move/3][move%3] = player[n];
		
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

 	
	public static void start(TicTacToe game)
	{
		In in = new In();
		while(true)
		{
			StdOut.print("Player " + game.player[game.currentPlayer] + " select you're move: ");
			int move = in.readInt() - 1;

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
		TicTacToe game = new TicTacToe();
		while(true)
		{
			start(game);
			StdIn.println("Do you want to play another game? y/n ");
			String str = in.readString();
			// TODO: Check if continue
		}
	}
}

