package damaPOP;

import java.util.Scanner;

public class DAMaPOP {
	// maxims
	final static byte MAXPOPS = 99;
	public final static byte MAXBETS = 99;
	final static byte BETSBUFFER = 10;

	public static void main(String[] args) {
		//
		Pop[] pop = new Pop[MAXPOPS];
		int totalPops = 0;

		String[][] lastBets = new String[BETSBUFFER][2];

		DAMaPOP a = new DAMaPOP();
		int o = 0;
		do {
			o = a.menu();
			switch (o) {
			case 1:
				totalPops = a.newPop(pop, totalPops);
				break;
			case 2:
				a.listPops(pop, totalPops, lastBets);
				break;
			case 3:
				a.showLastBets(lastBets, totalPops, pop);
				break;
			case 4:
				a.search(pop, totalPops);
				break;
			case 5:
				System.out.println("Fins un altra");
				break;
			}
		} while (o != 5);

	}
	public void search(Pop[] pop,int totalPops){
		if (pop[0] == null) {
			System.out.println("No existeixen articles per cercar en aquest moment\n");
		} else {
		System.out.println("##### Cercador del DAMaPOP #####");
		System.out.println("0-Sortir\n1-Cercar per nom\n2-Cercar per ciutat\n3-Cercar per preu");
		int o =0;
		do {
			o = readInt();
			switch (o) {
			case 1:
				searchByName(pop,totalPops);
				break;
			case 2:
				
				break;
			case 3:
				
				break;			
			case 0:				
				break;
			default:System.out.println("Opció no vàlida");
				break;
			}
		} while (o != 0);		
		}
	}
	
	public void searchByName(Pop[] pop,int totalPops){
		System.out.println("Escriu que cerques: (només es pot escriure una paraula)");
		String word = readString().toLowerCase();
		word=word.split(" ")[0];
		System.out.println("---------------- Resultats ---");
		for (int i = 0; i < totalPops; i++) {
			String[] name = pop[i].name.toLowerCase().split(" ");
			
			for(byte x=0;x<name.length;x++){
				byte count =1;
				//somnweoinwefowef  code hereeee
				do{
					
					
				}while(count<name[x].length());
				
			}
				
			
			
		}
		
	}

	public void showLastBets(String[][] lastBets, int totalPops, Pop[] pop) {
		System.out.println("#####  - Ultimes ofertes fetes pels usuaris - ######");
		if (lastBets[0][0] == null) {
			System.out.println("\nNo s'ha fet cap oferta\n");
		} else {
			for (byte x = (BETSBUFFER - 1); x >= 0; x--) {
				if (lastBets[x][0] != null) {
					int i = Integer.parseInt(lastBets[x][0]);
					System.out.println("\n-------------------------------- id: " + (x + 1) + " ---\n" + lastBets[x][1]
							+ "\nProducte: " + pop[i].name + " - " + pop[i].description + "\nLloc: " + pop[i].location
							+ "   Preu: " + pop[i].price + "€\n------------------------------------------");
				}
			}
			System.out
					.println("\nIntrodueix la id de la oferta per consultar totes les ofertes del producte (0=tornar)");
			int article = readInt();
			if (article != 0 && article <= BETSBUFFER) {
				article--;
				if (lastBets[article][0] != null) {
					listBets(Integer.parseInt(lastBets[article][0]), pop, lastBets, totalPops);
				} else {
					System.out.println("No exiteix ");
					showLastBets(lastBets, totalPops, pop);
				}
			}
		}
	}

	public int newPop(Pop[] pop, int position) {
		Pop temp = new Pop();
		System.out.println("###### Nou article ######");
		System.out.println("Escriu el nom de article:");
		temp.name = readString();
		System.out.println("Escriu-ne una petita descripció:");
		temp.description = readString();
		System.out.println("Escriu-ne el seu preu:");
		temp.price = readInt();
		System.out.println("Escriu la seva localització:");
		temp.location = readString();
		System.out.println("Vols guardar el producte?  si/no");
		if (readString().equals("si")) {
			System.out.println("Article guardat!\n");
			pop[position++] = temp;
		} else {
			System.out.println("Article anul·lat");
		}
		return position;

	}

	public int menu() {
		int op = 0;
		Scanner lector = new Scanner(System.in);
		do {
			op = 0;
			System.out.println("----------------------");
			System.out.println("DamaPop");
			System.out.println("----------------------");
			System.out.println("1-Nou article ");
			System.out.println("2-Llistar tots els articles ");
			System.out.println("3-Ultimes ofertes  ");
			System.out.println("4-Buscador");
			System.out.println("5-Sortir ");

			if (lector.hasNextInt()) {
				op = lector.nextInt();
				if (op > 4 || op <= 0) {
					System.out.println("Error introdueix una opció(1-4) ");
				}
			} else {
				System.out.println("Error introdueix una opció(1-4) ");
				lector.next();
			}

		} while (op > 4 || op <= 0);
		return op;
	}

	public void listPops(Pop[] pop, int totalPops, String[][] lastBets) {
		System.out.println("######## Articles  DAMaPOP ########");
		if (pop[0] == null) {
			System.out.println("No existeixen articles en aquest moment\n");
		} else {
			Scanner read = new Scanner(System.in);
			int article;
			for (int i = 0; i < totalPops; i++) {
				System.out.println("------------------------- id: " + (i + 1) + " ---");
				System.out.println(" " + pop[i].name + " - " + pop[i].description);
				System.out.println(" Lloc: " + pop[i].location + "   Preu: " + pop[i].price
						+ "€\n-----------------------------------\n");
			}
			System.out.println("Introdueix la id del article que vols consultar (0=tornar)");
			article = readInt();
			if (article != 0) {
				if (article <= totalPops) {
					listBets((article - 1), pop, lastBets,totalPops);
				} else {
					System.out.println("L'article introduït no existeix\n");
					listPops(pop, totalPops, lastBets);
				}
			}
		}
	}

	public void listBets(int position, Pop[] pop, String[][] lastBets,int totalPops) {
		System.out.println("###### Ofertes per " + pop[position].name + " ######\n");
		if (pop[position].totalBets == 0) {
			System.out.println("No hi ha ofertes encara");
			askToBet(position,pop,lastBets,totalPops);
		} else {
			for (byte x = 0; x < pop[position].totalBets; x++) {
				System.out.println("-" + pop[position].bet[x].user + " ofereix " + pop[position].bet[x].price
						+ "€ i diu " + pop[position].bet[x].comment);
			}
			askToBet(position,pop,lastBets,totalPops);
		}
	}
	
	public void askToBet(int position, Pop[] pop, String[][] lastBets,int totalPops){
		System.out.println("\nVols fer una nova oferta? si/no");
		String in = readString();
		boolean toBet=false;
		if (in.equals("si")) {
			toBet=true;
		} 			
		if (toBet) {
			newBet(position, pop, pop[position].totalBets, lastBets);
		}else{
			listPops(pop, totalPops, lastBets);
		}
	}

	public void betToBuffer(String[][] lastBets, Bet temp, int position) {
		for (byte x = (BETSBUFFER - 1); x > 0; x--) {
			lastBets[x][0] = lastBets[(x - 1)][0];
			lastBets[x][1] = lastBets[(x - 1)][1];
		}
		lastBets[0][0] = String.valueOf((position++));
		lastBets[0][1] = (temp.user + " ofereix " + temp.price + "€ i diu: " + temp.comment);
	}

	public void newBet(int position, Pop[] pop, int toBet, String[][] lastBets) {
		if (pop[position].totalBets < MAXBETS) {
			System.out.println("###### Nova oferta ######");
			Pop currentPop = pop[position];
			Bet temp = new Bet();
			System.out.println("Escriu et teu Nom:");
			temp.user = readString();
			System.out.println("Escriu l'import que ofereixes:");
			temp.price = readInt();
			System.out.println("Escriu el teu missatge:");
			temp.comment = readString();
			betToBuffer(lastBets, temp, position);// guardar al buffer dultimes
													// apostes
			currentPop.bet[toBet] = temp;
			currentPop.totalBets++;
			pop[position] = currentPop;
			System.out.println("Oferta guardada!\n");
		} else {
			System.out.println("No es poden fer més ofertes");
		}
	}

	private int readInt() {
		Scanner read = new Scanner(System.in);
		while (!read.hasNextInt()) {
			System.out.println("Error, escriu un numero.");
			read.next();
		}
		int input = read.nextInt();
		read.nextLine();
		return input;
	}

	private int readIntRange(int max, int min) {
		Scanner read = new Scanner(System.in);
		int input = 0;
		boolean fin = false;
		do {
			if (read.hasNextInt()) {
				input = read.nextInt();
				if (input < max && input >= min) {
					fin = false;
				} else {
					System.out.println("El valor " + input + " no exiteix");
				}
			} else {
				System.out.println("Error, escriu un numero.");
				read.next();
			}
		} while (fin);
		read.nextLine();
		return input;
	}

	private byte readByte() {
		Scanner read = new Scanner(System.in);
		while (!read.hasNextByte()) {
			System.out.println("Error, escriu un numero.");
			read.next();
		}
		byte input = read.nextByte();
		read.nextLine();
		return input;
	}

	private String readString() {
		Scanner read = new Scanner(System.in);
		String in = read.nextLine();
		while (in.length() < 2) {
			System.out.println("Error, el nom ha de tenir més de tres lletres");
			in = read.nextLine();
		}
		return in;
	}

}
