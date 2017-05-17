package collections;

import java.util.ArrayList;

/**
 * Ex16. Ara el pare de la Pepa li ha regalat un estoig màgic, podrà anar
 * afegint tants cromos repetits com vulgui és infinit. Com es defineix
 * 
 * @author #martic20
 *
 */
public class EstoigMagic {

	private ArrayList<Cromo> cromos;

	public EstoigMagic(ArrayList<Cromo> cromos) {
		this.setCromos(cromos);
	}

	public ArrayList<Cromo> getCromos() {
		return cromos;
	}

	public void setCromos(ArrayList<Cromo> cromos) {
		this.cromos = cromos;
	}
	
	public String toString() {
		String s = "Cromos: ";
		int index = 0;
		while (index < this.cromos.size()) {
			Cromo c = this.cromos.get(index++);
			int i = c.getProgramador();
			if (i != 0) {
				s += c.getLLenguatge() + String.valueOf(i) + ",";
			}
		}
		return s;
	}

}
