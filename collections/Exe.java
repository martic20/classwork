package collections;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author #martic20
 *
 */
public class Exe {

	public static void main(String[] args) {
		// Ex 1.
		Estoig estoig = new Estoig(new int[] { 10, 22, 36, 52, 70, 90, 112, 136, 162, 190 });
		/*
		 * Ex2. La Pepa els cromos que té repatits són 10, 22, 36, 52, 70, 90,
		 * 112 ,136, 162, 190 Inicialitzar els valors del vector. Fes-ho amb un
		 * bucle.
		 */

		/*
		 * Ex3. Quan esteu a punt d'arribar a Sant Antoni (el metro diu L2 -
		 * Universitat) repassant cromos us adoneu que el 70 us falta. Que hi ha
		 * un error! Horroooor! Pàniiic! Sort que a la butxaca porta el 42 que
		 * si que el teniu repetit. Ja està traieu el 70 de l'estoig i posareu
		 * el 42. Però l'estoig té una particularitat, si s'elimina un cromo,
		 * tots els altres es mouen un lloc endavant i només deixeu lloc a
		 * l'última posició. Realitzeu una funció que elimini un número donat
		 * d'un array donat. Modifiqui aquest array movent tots els cromos una
		 * posició i deixi la última buida
		 */
		estoig.remove(estoig.indexOf(70));
		// Ex4. Afegiu a la última posició el número 42
		estoig.set(42, 9);
		/*
		 * Ex 5. La Pepa es troba l'Alex, i li pregunta si té els cromos que li
		 * falten que són 23,36,48,90 Realitzeu una funció que donat un número
		 * de cromo et digui si el tens o no.
		 */
		// Ex 6.
		int[] alexQuestion = { 23, 36, 48, 90 };
		for (int v : alexQuestion) {
			System.out.println(estoig.indexOf(v) == -1 ? "No" : "Si");
		}
		// Ex7. Afegir al codi anterior que quan el tingueu, el traieu de
		// l'estoig perquè li doneu a l'Alex.
		for (int v : alexQuestion) {
			if (estoig.indexOf(v) == -1) {
				System.out.println("No tinc el " + v);
			} else {
				System.out.println("sí tinc el " + v + ". Te'l dono.");
				estoig.remove(estoig.indexOf(v));
			}
		}
		/*
		 * Ex8. L'Àlex us demana que li digueu quants cromos teniu repetits ara.
		 * Els cromos 0 no compten.
		 */
		System.out.println("Repes: " + estoig.count());
		// Ex9. L'Àlex demana si li podeu ensenyar quins números són
		System.out.println(estoig.toString());
		/*
		 * Ex10. La Pepa ja va acabar la col·lecció de cromos i s'ha comprat una
		 * de cromos de programadors! Aquest album classifica els programadors
		 * pel llenguatge. I té la particularitat que per cada llenguatge té 10
		 * programadors i té 4 llenguatges. Els cromos que té repetits són: el
		 * 1,2 i 3 de Java; el 2 ,3 i 4 Python i 3,4 i 5 de XSL. Quina
		 * estructura de dades permet guardar aquesta informació. Com la
		 * definiu.
		 */
		Estoig estoigProgramadors = new Estoig(new Cromo[] { new Cromo(1, "Java"), new Cromo(2, "Java"),
				new Cromo(3, "Java"), new Cromo(2, "Python"), new Cromo(3, "Python"), new Cromo(4, "Python"),
				new Cromo(3, "XSL"), new Cromo(4, "XSL"), new Cromo(5, "XSL") });
		/*
		 * Ex11. Ara es troba la Rosa i li demana si té el cromo 2 de Python
		 * repetit. Feu una funció que et digui si aquest cromo el té repetit o
		 * no la pepa
		 */
		System.out.println(estoigProgramadors.isRepeated(2, "Python") ? "El tinc repetit" : "No el tinc repetit");
		/*
		 * Ex13. Finalment la Pepa vol veure tots els cromos que té repetits.
		 * Podeu fer-ho sense for(;;)?
		 */
		System.out.println(estoigProgramadors.toString());
		/*
		 * Ex16. Ara el pare de la Pepa li ha regalat un estoig màgic, podrà
		 * anar afegint tants cromos repetits com vulgui és infinit. Com es
		 * defineix 
		 * Ex17. Fer el mateix per la primera col·lecció però amb aquest nou tipus de dades
		 * 10, 22, 36, 52, 70, 90, 112, 136, 162, 190
		 */
		EstoigMagic regalPare = new EstoigMagic(
				new ArrayList<Cromo>(Arrays.asList(new Cromo(10), new Cromo(22), new Cromo(36), new Cromo(52),
						new Cromo(70), new Cromo(90), new Cromo(112), new Cromo(136), new Cromo(162), new Cromo(190))));
		System.out.println(regalPare.toString());
	}
}
