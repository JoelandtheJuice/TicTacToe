package main.java.is.jatj.tictactoe.game;

public class TicTacToeController {
    public TicTacToeRepo gameRepo;
    private static int currentPlayer;
    
    public TicTacToeController() {
        gameRepo = new TicTacToeRepo();
        currentPlayer = 0;
    }

    public char[][] getBoard() {
        return gameRepo.getBoard();
    }

    public char getCurrentPlayerChar() {
        return gameRepo.getPlayer(currentPlayer);
    }

    public void updatePlayer() {
        currentPlayer = (currentPlayer + 1) % 2;
    }

    public boolean isMoveLegal(int move) {
        if (move < 0 || move > 8) {
            System.out.println("Out of bounds, try again");
            return false; }
        else if (gameRepo.board[move / gameRepo.BOARD_SIZE]
                [move % gameRepo.BOARD_SIZE] == 'X'
                || gameRepo.board[move / gameRepo.BOARD_SIZE]
                [move % gameRepo.BOARD_SIZE] == 'O') {
            System.out.println("This move has already been made");
            return false;
        }
        return true;
    }

    public void makeMove(int move) {
        gameRepo.board[move / gameRepo.BOARD_SIZE][move % gameRepo.BOARD_SIZE]
         = getCurrentPlayerChar();
        updatePlayer();
    }
    // for testing
    public void makeMove(int move, char ch) {
        gameRepo.board[move/3][move%3] = ch;
    }
    // for testing
    public char getCell(int row, int col) {
    	return gameRepo.board[row][col];
    }
    public boolean checkForWinner() {
        boolean winnerFlag = false;
        boolean rightAngle = true;
        boolean leftAngle = true;
        char currentChar = getCurrentPlayerChar();
        for (int i = 0; i < gameRepo.BOARD_SIZE; i++) {
            boolean vertical = true;
            boolean horizontal = true;
            for (int j = 0; j < gameRepo.BOARD_SIZE; j++) {
                if (gameRepo.board[i][j] != currentChar) {
                    vertical = false;
                }
                if (gameRepo.board[j][i] != currentChar) {
                    horizontal = false;
                }

            }
            if (vertical || horizontal) {
                winnerFlag = true;
                break;
            }

            if (gameRepo.board[i][i] != currentChar) {
                rightAngle = false;
            }
            if (gameRepo.board[i][2 - i] != currentChar) {
                leftAngle = false;
            }

        }
        if (rightAngle || leftAngle) {
            winnerFlag = true;
        }
        return winnerFlag;
    }
}
