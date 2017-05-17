package chessGame;

import java.util.ArrayList;

public abstract class Peça {
	protected byte[] posicio = new byte[2];
	protected boolean player;
	// !!!! this is a duplicated variable from ChessBoard that i don't know how
	// to obtain it !!!!!
	public final static int[] BOARDSIZE = { 7, 7 };

	/**
	 * @param y
	 *            :posició y (Up&down)
	 * @param x
	 *            :posició x (left&right)
	 * @param player:
	 *            Jugador a la que pertany. false = player A, true = player B.
	 */
	public Peça(int y, int x, boolean player) {
		this.posicio[0] = (byte) y;
		this.posicio[1] = (byte) x;
		this.player = player;
	}

	public byte[] getPosicio() {
		return this.posicio;
	}

	public char setPosicio(int y, int x, char[][] board) {
		char killed = '-';
		board[this.posicio[0]][this.posicio[1]] = '-';
		if (board[y][x] != '-') {
			killed = board[y][x];
		}
		board[y][x] = this.getName();
		return killed;
	}
	/**
	 * Give true if there's an enemy at the specified place
	 * @return
	 */
	protected boolean canKill(char piece){
		if(this.getPlayer(piece, !this.player)){
			return true;
		}		
		return false;
	}

	/**
	 * Makes shure the given coordinates are inside the board and there's not and owned piece
	 * @param y
	 * @param x
	 * @param board
	 * @param player
	 * @return
	 */
	protected boolean isValidPosition(int y, int x, char[][] board) {
		if (x < 0 || x > BOARDSIZE[1]) {//x is okey
			return false;
		}
		if (y < 0 || y > BOARDSIZE[0]) {//y is okey
			return false;
		}
		if (getPlayer(board[y][x], this.player)) {//there is not and owned piece at this place
			return false;
		}
		return true;
	}

	/**
	 * Copied method from class ChessBoard
	 * 
	 * @param piece:
	 *            the piece you want to check
	 * @param currentPlayer:
	 *            the current player that is operating
	 * @return
	 */

	protected boolean getPlayer(char piece, boolean currentPlayer) {
		if (currentPlayer) {// player B
			if (piece > 64 && piece < 91) {
				return true;
			}
		} else {
			if (piece > 96) {
				return true;
			}
		}
		return false;
	}

	public abstract ArrayList<Integer> movimentsPossibles(char[][] board);

	public abstract char getName();

	public String getPlayer() {
		if (this.player) {
			return "A";
		} else {
			return "B";
		}
	}

}
