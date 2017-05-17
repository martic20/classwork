package chessGame;

import java.util.ArrayList;

public class Reina extends Peça {
	// variables
	private static final char NAME = 'q';

	/**
	 * @param x
	 *            :posició x
	 * @param y
	 *            :posició y
	 * @param player
	 *            :Jugador a la que pertany. false = player A, true = player B.
	 */
	public Reina(int x, int y, boolean player) {
		super((byte) x, (byte) y, player);
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
		byte[][] posibilities = { { 1, 1 }, { -1, 1 }, { 1, -1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { 1, 0 },
				{ -1, 0 } };
		for (byte[] now : posibilities) {
			boolean more = true;
			byte ysum = now[0], xsum = now[1];
			do {
				y = (this.posicio[0] + now[0]);
				x = (this.posicio[1] + now[1]);
				if (this.isValidPosition(y, x, board)) {
					moviments.add(y * 10 + x);
					if (this.canKill(board[y][x])) {
						more = false;
					} else {
						now[0] += ysum;
						now[1] += xsum;
					}
				} else {
					more = false;
				}
			} while (more);
		}
		return moviments;
	}

}
