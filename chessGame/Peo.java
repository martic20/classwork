package chessGame;

import java.util.ArrayList;

public class Peo extends Peça {
	// direccio del peo depen del jugador (boolean player)
	// true positiva - false negativa

	private static final char NAME = 'p';

	public Peo(int y, int x, boolean player) {
		super(y, x, player);
	}

	public char getName() {
		if (this.player) {
			return NAME - 32;
		} else {
			return NAME;
		}
	}

	public ArrayList<Integer> movimentsPossibles(char[][] board) {
		// variables
		ArrayList<Integer> moviments = new ArrayList<Integer>();
		int increment = 0, x, y;
		// get his direction
		if (this.player) {
			increment = -1;
		} else {
			increment = 1;
		}
		// move one
		y = (this.posicio[0] + increment);
		x = (this.posicio[1]);
		if (this.isValidPosition(y, x,board)) {
			moviments.add(y * 10 + x);
		}
		// move two if it's the first time
		if ((this.player && this.posicio[0] == 6) || (!this.player && this.posicio[0] == 1)) {
			y = (this.posicio[0] + increment * 2);
			x = (this.posicio[1]);
			if (this.isValidPosition(y, x,board) ) {
				moviments.add(y * 10 + x);
			}
		}
		// diagonal if he can kill
		for (byte d = 1; d > -2; d -= 2) {
			y = this.posicio[0] + increment;
			x = this.posicio[1] + d;
			if (this.isValidPosition(y, x,board) && board[y][x] != '-') {
				moviments.add(y * 10 + x);
			}			
		}
		return moviments;
	}
}
