package collections;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author #martic20
 *
 */
public class Estoig {
	/*
	 * Ex 1. La Pepa va al mercat de Sant Antoni a canviar cromos. Per això
	 * porta un estoig que li ha deixat el seu pare on pot guardar 10. Aquest
	 * estoig permet veure pel llom el número del cromo. Amb quina estructura de
	 * dades guardeu aquesta informació?
	 */
	private final static int CAPACITAT = 10;
	private int[] cromos = new int[CAPACITAT];
	private String[] llenguatge = new String[CAPACITAT];

	/* Ex15. Feu el fet per a la col·lecció de Grans programadors!! */
	public Estoig(Cromo[] data) {
		if (data.length < CAPACITAT) {
			for (int x = 0; x < CAPACITAT; x++) {
				if (data.length > x) {
					this.cromos[x] = data[x].getProgramador();
					this.llenguatge[x] = data[x].getLLenguatge();
				} else {
					this.cromos[x] = Cromo.NULL;
					this.llenguatge[x] = Cromo.DEFAULT;
				}
			}
		} else {
			System.out.println("Error!!, a l'estoig nomes i caben 10 cromos");
		}
	}

	public Estoig(int[] data) {
		this.cromos = data.clone();
		for (int x = 0; x < CAPACITAT; x++) {
			this.llenguatge[x] = Cromo.DEFAULT;
		}
	}

	private final static byte NULL = 0;

	public int count() {
		int count = 0;
		for (int i : this.cromos) {
			if (i != 0) {
				count++;
			}
		}
		return count;
	}

	public String toString() {
		String s = "Cromos: ";
		int index = 0;
		while (index < this.cromos.length) {
			String p = this.llenguatge[index];
			int i = this.cromos[index++];
			if (i != 0) {
				s += p + String.valueOf(i) + ",";
			}
		}
		return s;
	}

	/*
	 * Ex3. Quan esteu a punt d'arribar a Sant Antoni (el metro diu L2 -
	 * Universitat) repassant cromos us adoneu que el 70 us falta. Que hi ha un
	 * error! Horroooor! Pàniiic! Sort que a la butxaca porta el 42 que si que
	 * el teniu repetit. Ja està traieu el 70 de l'estoig i posareu el 42. Però
	 * l'estoig té una particularitat, si s'elimina un cromo, tots els altres es
	 * mouen un lloc endavant i només deixeu lloc a l'última posició. Realitzeu
	 * una funció que elimini un número donat d'un array donat. Modifiqui aquest
	 * array movent tots els cromos una posició i deixi la última buida
	 */
	public int[] remove(int i) {
		int length = this.cromos.length;

		System.out.println("removed " + this.cromos[i]);
		if (i < length && i > -1) {
			while (i < (length - 1)) {
				this.cromos[i] = this.cromos[++i];
			}
			this.cromos[length - 1] = NULL;
		}
		return this.cromos;
	}

	public int indexOf(int value) {
		int count = 0;
		int result = NULL;
		for (int h : this.cromos) {
			if (h == value) {
				return count;
			} else {
				count++;
			}
		}
		return result;
	}

	public int indexOf(int value, int startPoint) {
		if (startPoint > (this.cromos.length - 1)) {
			return NULL;
		}
		for (int h = (startPoint); h < this.cromos.length; h++) {
			if (this.cromos[h] == value) {
				return h;
			}
		}
		return NULL;
	}

	public void set(int value, int index) {
		if (index < this.cromos.length && index > -1) {
			this.cromos[index] = value;
		}
	}

	public boolean isRepeated(int programador, String llenguatge) {
		llenguatge = Cromo.checkLanguage(llenguatge);
		boolean exists = false;
		for (int h = 0; h < CAPACITAT; h++) {
			if (this.llenguatge[h].equals(llenguatge)) {
				if (this.cromos[h] == programador) {
					if (exists) {
						return true;
					} else {
						exists = true;
					}
				}
			}
		}
		return false;
	}
}
