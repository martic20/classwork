package collections;

/**
 * 
 * @author #martic20
 *
 */

/*
 * Ex14. Un cromo? Què és un cromo? Només un número i una lletra? Definiu una
 * classe per als objectes Cromo
 */
public class Cromo {

	private int programador;
	private String llenguatge;

	public final static byte NULL = 0;
	private final static String[] LLENGUATGES = { "Java", "Python", "XSL", "Php" };
	public final static String DEFAULT = "";;

	public Cromo(int programador, String llenguatge) {
		if (programador < 1) {
			this.programador = NULL;
		} else {
			this.programador = programador;
		}
		this.llenguatge = checkLanguage(llenguatge);
	}
	public Cromo(int programador) {
		if (programador < 1) {
			this.programador = NULL;
		} else {
			this.programador = programador;
		}
		this.llenguatge = DEFAULT;
	}

	public static String checkLanguage(String llenguatge) {
		for (String l : LLENGUATGES) {
			if (l.equals(llenguatge)) {
				return l;
			}
		}
		return DEFAULT;
	}

	public int getProgramador() {
		return this.programador;
	}

	public String getLLenguatge() {
		return this.llenguatge;
	}
}
