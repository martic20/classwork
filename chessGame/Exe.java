package chessGame;

public class Exe {
	public static void main(String[] args) {
System.out.println("\n### Welcome to the Biggest Chess Game ###\n");
		boolean currentPlayer = false;// false->Player A, true->Player B
		ChessBoard game = new ChessBoard();
		while (true) {
			game.printBoard();
			game.listPieces(currentPlayer);
			game.movePiece(currentPlayer);
			
			if (currentPlayer) {
				currentPlayer = false;
			} else {
				currentPlayer = true;
			}
		}

	}
	/*
	 * public void provaPeo() { Peo peo1 = new Peo((byte) 1, (byte) 0, true);
	 * byte[][] moviments = peo1.movimentsPossibles();
	 * System.out.println("moviments:"); for (byte[] g : moviments) {
	 * System.out.println("x:" + g[0] + " y:" + g[1]); }
	 * 
	 * peo1.setPosicio(moviments[0][0], moviments[0][1]);
	 * 
	 * System.out.println("\n"); moviments = peo1.movimentsPossibles();
	 * System.out.println("moviments:"); for (byte[] g : moviments) {
	 * System.out.println("x:" + g[0] + " y:" + g[1]); }
	 * 
	 * peo1.setPosicio(moviments[0][0], moviments[0][1]);
	 * 
	 * System.out.println("\n"); moviments = peo1.movimentsPossibles();
	 * System.out.println("moviments:"); for (byte[] g : moviments) {
	 * System.out.println("x:" + g[0] + " y:" + g[1]); } }
	 */
}
