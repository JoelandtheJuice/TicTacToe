package main.java.is.jatj.tictactoe.game;

public class TicTacToeController {
    public TicTacToeRepo gameRepo;
    public static int currentPlayer;
    
    public TicTacToeController() {
        gameRepo = new TicTacToeRepo();
        currentPlayer = 0;
    }
    public void startNewGame()
    {
    	gameRepo.switchPlayers();
    	currentPlayer = 1;
    	gameRepo.initializeBoard();
    }
    public int getPlayerScore(int n) {
    	return gameRepo.getScore(n);
    }
    
    public char[][] getBoard() {
        return gameRepo.getBoard();
    }
    
    public char getPlayerChar(int n) {
    	return gameRepo.getPlayer(n);
    }
    public char getCurrentPlayerChar() {
        return gameRepo.getPlayer(currentPlayer);
    }
    public int getCurrentPlayer()
    {
    	return currentPlayer;
    }
    public void updatePlayer() {
        currentPlayer = (currentPlayer + 1) % 2;
    }
    public boolean isMoveLegal(int move) {
        if (move < 0 || move > 8) {
            return false; }
        else if (gameRepo.board[move / gameRepo.BOARD_SIZE]
                [move % gameRepo.BOARD_SIZE] == 'X'
                || gameRepo.board[move / gameRepo.BOARD_SIZE]
                [move % gameRepo.BOARD_SIZE] == 'O') {
            		return false;
        }
        return true;
    }

    public void makeMove(int move) {
    	if(isMoveLegal(move)) {
            gameRepo.board[move / gameRepo.BOARD_SIZE][move % gameRepo.BOARD_SIZE]
                    = getCurrentPlayerChar();
    	}
    }
    // for testing
    public void makeMove(int move, char ch) {
        gameRepo.board[move / gameRepo.BOARD_SIZE][move % gameRepo.BOARD_SIZE] = ch;
    }
    // for testing
    public char getCell(int row, int col) {
    	return gameRepo.board[row][col];
    }
    private void updateScore() {
    	gameRepo.setScore(currentPlayer);
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
        
        if(winnerFlag) {
        	updateScore();
        }
        return winnerFlag;
    }
}
