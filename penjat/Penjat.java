//@martic20
//16-11-16
package penjat;

import java.util.Random;
import java.util.Scanner;

public class Penjat {

	final static byte NUMPARAULES = 7;//7
	final static byte PUNTS = 100;
	final static byte INTENTS = 5;

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner read = new Scanner(System.in);

		// variables
		String paraules[] = { "silenci","xai", "historia","joc","permutar","compilador","estima" };
		String solucio, nom;
		byte llargada, punts = PUNTS, intents = INTENTS;
		boolean fin,finall,last3=false;

		
		// preguntar nom
		System.out.println("Introdueix el teu nom:");		
		fin = true;
		do {
			nom = read.next();
			if (nom.length() > 2) {
				fin = false;
			} else {
				System.out.println("Error, el nom ha de ser de mínim tres lletres. ");
			}
			read.nextLine();
		} while (fin);
		 

		// escollir paraula
		solucio = paraules[rand.nextInt(NUMPARAULES)];
		llargada = (byte) (solucio.length());
		
		//iniciar joc
		finall =true;
		do{
		
		// paraula o pista?
		System.out.println(" ");
		System.out.println("Tens "+punts+" punts i " +intents+" intents.");
		System.out.println("Triar la teva opció o escriu la paraula directament:");
		System.out.println("1 - Mostrar la lletra d'una posició determinada. -20 pt.");
		System.out.println("2 - Mostrar num. lletres de la paraula. -20 pt.");
		System.out.println("3 - Mostrar quants cops apareix una lletra. -20 pt.");
		System.out.println("4 - Mostrar consonants. -30 pt.");
		
		if (read.hasNextByte()) {
			int tria = read.nextByte();
			switch (tria) {
			
			//Mostrar la lletra d'una posició determinada.
			case 1:
				if (punts >= 20) {
					fin = true;
					System.out.println("Escriu la posició: (Només tens un intent, compte amb els num. grans)");					
					do {
						if (read.hasNextByte()) {
							int p = read.nextByte();
							if (p <= llargada && p >0) {
								System.out.println("La lletra és:  " + solucio.charAt(p-1));
								fin = false; 
								punts-=20;
							} else {
								System.out.println("La paraula no té tantes lletres.");
								fin=false;
								punts-=20;
							}
						} else {
							System.out.println("Error, escriu un num. ");
						}
						read.nextLine();
					} while (fin&&intents==0);
				} else
					{System.out.println("No tens prous punts :(");}
				break;
				
			//Mostrar num. lletres de la paraula. 
			case 2:
				if (punts >= 20) {			
					System.out.println("La paraula té " + llargada + " lletres.");
					punts-=20;
				} else
					{System.out.println("No tens prous punts :(");}
				break;
				
			//Mostrar quants cops apareix una lletra.
			case 3:
				if (punts >= 20) {
					System.out.println("Escriu la lletra:");
					fin = true;
					do {
						if (read.hasNext()) {
							String lletra = read.next();
							byte n = 0;
							for (byte x = 0; x < llargada; x++) {
								if (String.valueOf(solucio.charAt(x)).equals(lletra.toLowerCase())) {
									n++;
								}
							}
							System.out.println("La lletra " + lletra + " apareix " + n + " cops");
							punts-=20;
							fin=false;
						} 
						read.nextLine();
					} while (fin);
				} else
					{System.out.println("No tens prous punts :(");}
				break;
			
			//Mostrar consonants.
			case 4:				
				if (punts >= 30) {
					for (byte x = 0; x < llargada; x++) {					
						switch(solucio.charAt(x)) {
				        	case 'a': 	case 'e':	case 'i':	case 'o':	case 'u':
				        		System.out.print("[*]");
				            break;
				        default:
				        	System.out.print("["+solucio.charAt(x) + "]");
				        	break;			            
						}							
					}
					System.out.println("");
					for (byte x = 0; x < llargada; x++) {	
						System.out.print(" "+(x+1)+" ");
					}
					punts-=30;
					System.out.println("");
				} else
					{System.out.println("No tens prous punts :(");}
			default:

			}

			fin = false;
		} else {
			//si introdueix text en contes de numero:
			String input = read.next();
			if(input.equals(solucio)){					
					finall=false;						
			}
				else {
					System.out.println("No has endevinat la paraula. ");
					--intents;
					punts -= 15;
					if (last3 == false) {
						if (input.length() > 3) {
							if (solucio.substring(llargada - 3, llargada)
									.equals(input.substring((input.length() - 3), input.length()))) {
								System.out.println("Però has endevinat les tres últimes lletres! Tens 25 punts addicional!");
								last3=true;
							}
						}
					}
				}		
		}
		//s'acaba la partida
		}while (finall&&intents!=0&&punts!=0);

		//resultat del joc
		if(intents==0||punts==0){
			System.out.println("S'han acabat els intents o punts. Has perdut");
			System.out.println("La paraula era:");
			for (byte x = 0; x < llargada; x++) {
				System.out.print("[" + solucio.charAt(x) + "]");
			}
		}else{
		//El nom del jugador s'ha de mostrar primera en minúscula i la última en majúscula.
		String r,t;		
		r=nom.substring(0,1);
		t=nom.substring(nom.length()-1,nom.length());		
		nom=(r.toLowerCase()+nom.substring(1, nom.length()-1)+t.toUpperCase());			

		System.out.println("Felicitats "+nom.concat("! Has guanyat amb "+punts+" punts i "+intents+" intents." ));
		System.out.println();
		System.out.print("Paraula: ");
		for (byte x = 0; x < llargada; x++) {
			System.out.print(solucio.charAt(x) + "-");
		}
		}
	}
}
