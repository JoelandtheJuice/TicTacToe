package main.java.is.jatj.tictactoe.game;

import java.util.Scanner;

public final class TicTacToe {
    static TicTacToeController gameController;

    private TicTacToe() {
        gameController = new TicTacToeController();
    }

     public static int getInput() {
         Scanner reader = new Scanner(System.in);
         int input = reader.nextInt() - 1;
         return input;
     }

    private static void start() {
        while (true) {
            displayBoard();
            System.out.print("Player " + gameController.getCurrentPlayerChar() + " select your move: ");
            int move = getInput();

            gameController.makeMove(move);
            // TODO: Check for win
            System.out.println();

        }
    }
    private static void displayBoard() {
        char[][] board = gameController.getBoard();
        for (int i = 0; i < gameController.gameRepo.BOARD_SIZE; i++) {
            for (int j = 0; j < gameController.gameRepo.BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    };

    public static void main(String[] args) {
        TicTacToe view = new TicTacToe();
        while (true) {
            view.start();
            System.out.println("Do you want to play another game? y/n ");
            // TODO: Check if continue
            // TODO: score and switchplayer
        }
    }
}
