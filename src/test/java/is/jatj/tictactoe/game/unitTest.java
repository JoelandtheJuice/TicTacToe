
package test.java.is.jatj.tictactoe.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import test.java.is.jatj.tictactoe.game.TicTacToeTest;

public class unitTest{

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("test.java.is.jatj.tictactoe.game");
	
	}

	TicTacToeTest game = new TicTacToeTest();

	// prófa að gera eitt move
	@Test
	public void testMakeMove() {
		game.makeMove(0, 0);
		assertEquals('O', game.board[0][0]);
	}

	// sjá hvort að playerinn verður til rétt
	@Test
		public void testinitializePlayer() {
			game.initializePlayer();
			assertEquals('O', game.player[0]);
			assertEquals('X', game.player[1]);
		}

		// sjá hvort að borðið verður ekki til rétt
		@Test
		public void testintializeBoard() {
			game.initializeBoard();
			char c[] = new char[9];
			c[0] = '1';
			c[1] = '2';
			c[2] = '3';
			c[3] = '4';
			c[4] = '5';
			c[5] = '6';
			c[6] = '7';
			c[7] = '8';
			c[8] = '9';
			
			int co = 0;
			for(int i = 0; i < 3; i++)
			{
				
				for(int j = 0; j < 3; j++)
				{
					
					assertEquals(c[co], game.board[i][j]);
					co++;
				}
			}	
		}
		// test til að tjekka á winnerum í leiknum
		@Test
		public void testWinnerH() {
			game.makeMove(0, 0);
			game.makeMove(1, 0);
			game.makeMove(2, 0);
			assertEquals(true, game.checkForWinner());
			
		}	
		
		@Test
		public void testWinnerV() {
			game.makeMove(0, 0);
			game.makeMove(3, 0);
			game.makeMove(6, 0);
			assertEquals(true, game.checkForWinner());
			
		}
		@Test
		public void testWinnerA() {
			game.makeMove(0, 0);
			game.makeMove(4, 0);
			game.makeMove(8, 0);
			assertEquals(true, game.checkForWinner());
			
		}

		// hérna erum við að testa nokkur move
		// hvort það virki og hvernig hann bregst við 
		// röngum inputum
		@Test
		public void testmove() {
			assertEquals(true, game.isMoveLegal(1));
		}
		@Test
		public void testmovetohigh() {
			assertEquals(false, game.isMoveLegal(11));
		}
		@Test
		public void testmovenegative() {
			assertEquals(false, game.isMoveLegal(-1));
		}
		@Test
		public void testmoveDoneBefore() {
			game.makeMove(1, 0);
			assertEquals(false, game.isMoveLegal(1));
		}

		
}