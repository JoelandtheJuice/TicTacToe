package main.java.is.jatj.tictactoe.game;

public class TicTacToeRepo {
    char[][] board;
    private char[] player;
    public static final int BOARD_SIZE = 3;

    TicTacToeRepo() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        initializePlayer();
        initializeBoard();
    }
    private void initializePlayer() {
        player = new char[2];
        player[0] = 'O';
        player[1] = 'X';
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

