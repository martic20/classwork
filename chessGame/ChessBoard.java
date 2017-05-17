package chessGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChessBoard {
	/*
	 * Pieces: K: King Q: Queen R: Rook -> torre B: Bishop ->alfil N: Knight P:
	 * Pawn
	 * 
	 * Player A is false, in lowerCase and displayed at the top --------------
	 * Player B is true, in upperCase and displayed at the bottom ------------
	 */
	public final static int[] BOARDSIZE = { 8, 8 };

	private char[][] board = new char[BOARDSIZE[0]][BOARDSIZE[1]];

	private final static char[][] INIT_BOARD = { { 'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r' },
			{ 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' }, { '-', '-', '-', '-', '-', '-', '-', '-', },
			{ '-', '-', '-', '-', '-', '-', '-', '-', }, { '-', '-', '-', '-', '-', '-', '-', '-', },
			{ '-', '-', '-', '-', '-', '-', '-', '-', },

			{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' }, { 'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R' } };

	public ChessBoard() {
		this.board = INIT_BOARD;
	}

	/**
	 * 
	 * @param player
	 *            :Used to show the pieces from the player A (false) or B (true)
	 */
	public ArrayList<Integer> listPieces(boolean player) {
		String now;
		if (player) {
			now = "B";
		} else {
			now = "A";
		}
		System.out.println("\nPlayer " + now + " is playing.");
		ArrayList<Integer> pieceList = new ArrayList<Integer>();
		for (byte y = 0; y < BOARDSIZE[0]; y++) {
			for (byte x = 0; x < BOARDSIZE[1]; x++) {
				char piece = this.board[y][x];
				String name;
				if ((name = getPieceName(piece, player)) != null) {
					// disable the comment to view all your pieces detailed
					// System.out.println("At " + y + "" + x + " a " + name);
					pieceList.add(y * 10 + x);
				}
			}
		}
		return pieceList;
	}

	/**
	 * 
	 * @param options
	 *            : Possible pieces to use in coordinates (yx)
	 */
	public void movePiece(boolean player) {
		boolean fin = true;
		ArrayList<Integer> moviments;
		int piece, x, y;
		Peça myPiece;
		// get moves list
		do {
			do {
				System.out.println("Select a valid piece you will use:");
				piece = readCoordinate();
				y = gety(piece);
				x = getx(piece);
				// System.out.println(getPlayer(board[y][x], player) + " " +
				// player);
			} while (!(y < BOARDSIZE[0] && x < BOARDSIZE[1] && getPlayer(board[y][x], player)));
			myPiece = getPiece(y, x);			
			moviments = myPiece.movimentsPossibles(this.board);
			// cloning every time to a new board
			// this is not a good option for the game perfomance
			// better do it (changing the content) while printing. A feature to
			// do
			
			char[][] tempBoard = this.board.clone();
			for (byte i = 0; i < tempBoard.length; i++) {
				tempBoard[i] = this.board[i].clone();
			}
			String text = "";
			for (int g : moviments) {
				y = gety(g);
				x = getx(g);
				if (this.board[y][x] != '-') {
					text = text.concat("At " + y + "" + x + " you can kill a " + getPieceName(this.board[y][x]) + "\n");
					tempBoard[y][x] = '$';
				} else {
					text = text.concat("At " + y + "" + x + "\n");
					tempBoard[y][x] = '#';
				}
			}
			printBoard(tempBoard);
			if (moviments.isEmpty()) {
				System.out.println("The're no possible movements");
			} else {
				System.out.println("\nPossible movements:");
				System.out.println(text);
				fin = false;
			}
		} while (fin);

		// make the move
		do {
			System.out.println("Enter a valid choice:");
			piece = readCoordinate();
		} while (!moviments.contains(piece));
		y = gety(piece);
		x = getx(piece);
		char killed;
		if ((killed = myPiece.setPosicio(y, x, this.board)) != '-') {
			System.out.println("### A " + getPieceName(killed) + " from player " + myPiece.getPlayer()
					+ " has been killed. ###\n");
		}
	}

	/**
	 * Convert from int (yx) coordinate to a separated x y values
	 * 
	 * @param g
	 * @return x
	 */
	public int getx(int g) {
		if (g < 10) {
			return g;
		} else {
			return g % 10;
		}
	}

	/**
	 * Convert from int (yx) coordinate to a separated x y values
	 * 
	 * @param g
	 * @return y
	 */
	public int gety(int g) {
		if (g < 10) {
			return 0;
		} else {
			if (g < 100) {
				return g / 10;
			} else {
				return (g % 100) / 10;
			}
		}
	}

	/**
	 * Read a coordinate that exists in the input list
	 * 
	 * @param options
	 * @return
	 */
	public int readCoordinate() {
		Scanner read = new Scanner(System.in);
		while (!read.hasNextInt()) {
			System.out.println("The value have to be a valid coordinate");
			read.nextLine();
		}
		return read.nextInt();
	}

	public Peça getPiece(int y, int x) {
		if (y < BOARDSIZE[0] && x < BOARDSIZE[1]) {
			char name = this.board[y][x];
			switch (name) {
			case 'p':
				return new Peo(y, x, false);
			case 'P':
				return new Peo(y, x, true);
			case 'n':
				return new Cavall(y, x, false);
			case 'N':
				return new Cavall(y, x, true);
			case 'q':
				return new Reina(y, x, false);
			case 'Q':
				return new Reina(y, x, true);
			case 'r':
				return new Torre(y, x, false);
			case 'R':
				return new Torre(y, x, true);
			case 'b':
				return new Alfil(y, x, false);
			case 'B':
				return new Alfil(y, x, true);
			case 'k':
				return new Rei(y, x, false);
			case 'K':
				return new Rei(y, x, true);
			}
		}
		return null;

	}

	/**
	 * 
	 * @param piece:
	 *            the piece you want to check
	 * @param currentPlayer:
	 *            the current player that is operating
	 * @return
	 */

	public boolean getPlayer(char piece, boolean currentPlayer) {
		if (currentPlayer) {// player B
			if (piece > 64 && piece < 91) {
				return true;
			} else {
				return false;
			}
		} else {// player A
			if (piece > 96) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Any piece
	 * 
	 * @param piece
	 * @return
	 */
	private String getPieceName(char piece) {
		switch (piece) {
		case 'k':
		case 'K':
			return "King";
		case 'q':
		case 'Q':
			return "Queen";
		case 'r':
		case 'R':
			return "Rook";
		case 'b':
		case 'B':
			return "Bishop";
		case 'n':
		case 'N':
			return "Knight";
		case 'p':
		case 'P':
			return "Pawn";
		}
		return null;
	}

	/**
	 * Only the pieces from the specific player
	 * 
	 * @param piece
	 * @return
	 */
	private String getPieceName(char piece, boolean player) {
		if (player) {
			switch (piece) {
			case 'K':
				return "King  ";
			case 'Q':
				return "Queen ";
			case 'R':
				return "Rook  ";
			case 'B':
				return "Bishop";
			case 'N':
				return "Knight";
			case 'P':
				return "Pawn  ";
			}
		} else {
			switch (piece) {
			case 'k':
				return "King  ";
			case 'q':
				return "Queen ";
			case 'r':
				return "Rook  ";
			case 'b':
				return "Bishop";
			case 'n':
				return "Knight";
			case 'p':
				return "Pawn  ";
			}
		}
		return null;
	}

	/**
	 * Print a temporal possible board
	 * 
	 * @param board
	 */
	public void printBoard(char[][] board) {
		printHeader();
		byte indey = 0;
		for (char[] c : board) {
			String line = (indey++) + " |";
			for (char x : c) {
				line = line + x + "|";
			}
			System.out.println(line);
			// printRow();
		}
	}

	/**
	 * Print the current board
	 */
	public void printBoard() {
		printBoard(this.board);
	}

	private void printHeader() {
		System.out.println("   0 1 2 3 4 5 6 7 ");
	}
}
