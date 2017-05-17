package chessGame;

import java.util.ArrayList;

public class Cavall extends Peça {
	//variables
	private static final char NAME = 'n';
	
	
	/**	 
	 * @param x :posició x
	 * @param y :posició y
	 * @param player :Jugador a la que pertany. false = player A, true = player B.
	*/
	public Cavall(int x, int y,boolean player) {
		super((byte) x, (byte) y,player);
	}
	
	public char getName() {
		if (this.player) {
			return NAME - 32;
		} else {
			return NAME;
		}
	}

	public ArrayList<Integer> movimentsPossibles(char[][] board) {
		ArrayList<Integer> moviments = new ArrayList<Integer>();
		int x, y;
		byte[][] posibilities = { { -2, 1 }, { 2, 1 }, { 1, 2 }, { 1, -2 }, { -2, -1 }, { 2, -1 }, { -1, 2 },
				{ -1, -2 } };
		for (byte[] option : posibilities) {
			y = (this.posicio[0] + option[0]);
			x = (this.posicio[1] + option[1]);
			if (this.isValidPosition(y, x,board)) {
				moviments.add(y * 10 + x);
			}
		}		
		return moviments;
	}

	
}
