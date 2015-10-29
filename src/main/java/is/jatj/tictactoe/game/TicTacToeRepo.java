package main.java.is.jatj.tictactoe.game;

public class TicTacToeRepo {
    char[][] board;
    private char[] player;
    private int score[];
    public static final int BOARD_SIZE = 3;

    TicTacToeRepo() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        score = new int[2];
        initializePlayer();
        initializeBoard();
    }
    private void initializePlayer() {
        player = new char[2];
        player[0] = 'O';
        player[1] = 'X';
        score[0] = 0;
        score[1] = 0;
    }
    public void setScore(int n) {

    }
    public int getScore(int n) {
    	return score[n];
    }
    public char[][] getBoard() {
        return board;
    }

    public char getPlayer(int n) {
        return player[n];
    }
    private void initializeBoard() {
        int counter = 1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = (char) ('0' + counter);
                counter++;
            }
        }
    }
}

