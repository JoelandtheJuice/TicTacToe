package main.java.is.jatj.tictactoe.game;

import java.util.Scanner;

public class TicTacToeRepo {
	char[][] board;
	private char[] player;
	
	TicTacToeRepo()
	{
		board = new char[3][3];
		initializePlayer();
		initializeBoard();
	}
	private void initializePlayer()
	{
		player = new char[2];		
		player[0] = 'O';
		player[1] = 'X';
	}
	
	public char[][] getBoard()
	{
		return board;
	}
	
	public char getPlayer(int n)
	{
		return player[n];
	}
	private void initializeBoard()
	{
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
}

