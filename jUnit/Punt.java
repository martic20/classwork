package jUnit;

public class Punt {

	private int x, y; // coordenades del punt

	public Punt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Punt() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void suma(Punt p) {
		x += p.x;
		y += p.y;
	}

}
