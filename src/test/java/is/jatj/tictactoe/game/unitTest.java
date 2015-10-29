
package test.java.is.jatj.tictactoe.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.java.is.jatj.tictactoe.game.TicTacToeController;

public class unitTest{

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("test.java.is.jatj.tictactoe.game");
	
	}

	TicTacToeController gameTest = new TicTacToeController();

	// prófa að gera eitt move
	@Test
	public void testMakeMove() {
		char ch = gameTest.getCurrentPlayerChar();
		gameTest.makeMove(0, ch);
		assertEquals(ch, gameTest.getCell(0, 0));
	}

	// sjá hvort að playerinn verður til rétt
	@Test
		public void testPlayerCharacters() {
			assertEquals('O', gameTest.getCurrentPlayerChar());
			gameTest.updatePlayer();
			assertEquals('X', gameTest.getCurrentPlayerChar());
		}

		// sjá hvort að borðið verður ekki til rétt
		@Test
		public void testBoard() {
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
					
					assertEquals(c[co], gameTest.getCell(i, j));
					co++;
				}
			}	
		}
		// test til að tjekka á winnerum í leiknum
		@Test
		public void testWinnerH() {
			gameTest.makeMove(0, 'O');
			gameTest.makeMove(1, 'O');
			gameTest.makeMove(2, 'O');
			assertEquals(true, gameTest.checkForWinner());
			
		}	
		
		@Test
		public void testWinnerV() {
			gameTest.makeMove(0, 'O');
			gameTest.makeMove(3, 'O');
			gameTest.makeMove(6, 'O');
			assertEquals(true, gameTest.checkForWinner());
			
		}
		@Test
		public void testWinnerA() {
			gameTest.makeMove(0, 'O');
			gameTest.makeMove(4, 'O');
			gameTest.makeMove(8, 'O');
			assertEquals(true, gameTest.checkForWinner());
			
		}

		// hérna erum við að testa nokkur move
		// hvort það virki og hvernig hann bregst við 
		// röngum inputum
		@Test
		public void testmove() {
			assertEquals(true, gameTest.isMoveLegal(1));
		}
		@Test
		public void testmovetohigh() {
			assertEquals(false, gameTest.isMoveLegal(11));
		}
		@Test 	
		public void testmovenegative() {
			assertEquals(false, gameTest.isMoveLegal(-1));
		}
		@Test
		public void testmoveDoneBefore() {
			gameTest.makeMove(1, 'O');
			assertEquals(false, gameTest.isMoveLegal(1));
		}
		@Test
		public void checkScore() {
			gameTest = new TicTacToeController();
			gameTest.makeMove(0, 'O');
			gameTest.makeMove(1, 'O');
			gameTest.makeMove(2, 'O');
			gameTest.checkForWinner();
			assertEquals(1, gameTest.getPlayerScore(0));
		}

		@Test
		public void testswitchplayers() {
			gameTest = new TicTacToeController();
			gameTest.makeMove(0, 'O');
			gameTest.makeMove(1, 'O');
			gameTest.makeMove(2, 'O');
			assertEquals('X', gameTest.getCurrentPlayerChar[0]);
		}


		
}