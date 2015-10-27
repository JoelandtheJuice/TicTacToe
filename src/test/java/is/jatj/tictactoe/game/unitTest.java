
package test.java.is.jatj.tictactoe.game;



public class unitTest{

	public static void main(String args[]) {
	org.junit.runner.JUnitCore.main("test.java.is.jatj.tictactoe.game");
	    }

	    TicTacToe game = new TicTacToe();

		@Test
		public void testMakeMove() {
			
			game.makeMove(1, 0);
			assertEquals('O', game.board[0][0]);
		}
}