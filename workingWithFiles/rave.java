package a2Festival.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Comparator;

public class rave {
	// files properties
	final static String INPUTFILENAME = "artistes";
	final static String EXTENSION = ".csv";
	final static String BACKUP_EXTENSION = ".bak";
	final static String DIR = System.getProperty("user.dir");
	// columnes que tindrà el fitxer csv d'entrada
	final static byte FILE_COLUMNS = 5;
	// Composed by {{StageName,StyleName1,StyleName2},
	// {Stage2Name,StyleName}};
	final static String[][] STYLES = { { "sexpistols", "punk" }, { "jamaica", "reggae" }, { "centralpark", "folk" },
			{ "neworleans", "blues" }, { "lazona", "reggaetoon", "hiphop", "rock" }, { "off", "" } };
	// in input file
	final static byte COLUMN_OF_STYLE = 1;	

	public static void main(String[] args) {
		rave r = new rave();
		// fileDirs={inputFileDir, outputFileDir, backupFileDir}
		String[] fileDirs = { DIR, DIR, DIR };
		String[][] fileContent;
		// menu
		int option = 1;
		// way of writing files, appending(true) or overwriting(false)
		boolean append;
		append= r.setConfiguration(fileDirs);
		do {
			System.out.println("\n### Eat Slepp Rave Repeat ###" + "\n1-Sortir" + "\n2-Crear fitxers"
					+ "\n3-Crear copies de seguretat" + "\n4-Eliminar copies de seguretat"
					+ "\n5-Restaurar copies de seguretat");
			option = r.readNumber();
			switch (option) {
			case 1:
				System.out.println("Fins una altra!");
				break;
			case 2:
				fileContent = r.getFileData(fileDirs[0] + "/" + INPUTFILENAME + EXTENSION);
				if (!fileContent[0][0].equals("null")) {
					r.generateFiles(fileContent, fileDirs[1],append);
				}
				break;
			case 3:
				r.makeBackup(fileDirs);
				break;
			case 4:
				r.deleteBackup(fileDirs);
				break;
			case 5:
				r.restoreBackup(fileDirs);
				break;
			default:
				System.out.println("Opció invàlida");
				break;
			}
		} while (option != 1);

	}

	// show files from a directory
	public boolean ls(String dir, String extension) {
		try {
			File folder = new File(dir);
			File[] list = folder.listFiles();
			if (list.length < 1) {
				System.out.println("No hi han fitxers.");
				return false;
			}
			System.out.println("Fitxers dins de " + dir + " :\n");
			for (int x = 0; x < list.length; x++) {
				if (list[x].isFile()) {
					if (list[x].getName().toLowerCase().endsWith(extension))
						System.out.println(list[x].getName());
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("Directori no vàlid. Error: " + e);
		}
		return false;
	}

	// restore a backup
	public void restoreBackup(String[] fileDirs) {
		System.out.println("## Copies existents ##");
		if (ls(fileDirs[2], BACKUP_EXTENSION)) {
			System.out.println("\nEscriu el nom de la copia que vols restaurar:");
			String toRestore = readString();
			toRestore = fileDirs[2] + "/" + toRestore;
			if (isAFile(toRestore)) {
				try {
					File fromfile = new File(toRestore);
					String initFile = fileDirs[0] + "/" + INPUTFILENAME + EXTENSION;
					//if the file already exists
					if (isAFile(initFile)) {
						File file = new File(initFile);
						if (file.delete()) {
							System.out.println("El fitxer ja existia, ha estat eliminat");
						}
					}
					File tofile = new File(initFile);
					Files.copy(fromfile.toPath(), tofile.toPath());
					System.out.println("Backup restaurada correctament");
				} catch (Exception e) {
					System.out.println("Error al intentar restaurar la copia");
				}
			} else {
				System.out.println("EL fitxer no existeix" + toRestore);
			}
		}
	}

	// delete backup files
	public void deleteBackup(String[] fileDirs) {
		System.out.println("## Copies existents ##");
		if (ls(fileDirs[2], BACKUP_EXTENSION)) {
			System.out.println("\nEscriu el nom del fitxer que vols eliminar:");
			String toDelete = readString();
			try {
				File file = new File(fileDirs[2] + "/" + toDelete);
				if (file.delete()) {
					System.out.println("\nOperació realitzada correctament\n");
				} else {
					System.out.println("\nError al intentar borrar el fitxer\n");
				}
			} catch (Exception e) {
				System.out.println("\nError al intentar borrar el fitxer\n");
			}
		}
	}

	// make bakcup file
	public void makeBackup(String[] fileDirs) {
		if (isAFile(fileDirs[0] + "/" + INPUTFILENAME + EXTENSION)) {
			try {
				File fromfile = new File(fileDirs[0] + "/" + INPUTFILENAME + EXTENSION);
				File tofile = new File(
						fileDirs[2] + "/" + LocalDateTime.now() + "-" + INPUTFILENAME + BACKUP_EXTENSION);
				Files.copy(fromfile.toPath(), tofile.toPath());
				System.out.println("Backup creada correctament");
			} catch (Exception e) {
				System.out.println("Error al fer el backup");
			}
		} else {
			System.out.println("!! No existeix el fitxer d'origen !!");
		}
	}

	// method to sort an two dimensional array
	public void sortArray(String[][] fileContent) {
		Arrays.sort(fileContent, new Comparator<String[]>() {
			@Override
			public int compare(final String[] entry1, final String[] entry2) {
				if (entry1[4].length() == 4) {
					entry1[4] = "0" + entry1[4];
				}
				if (entry2[4].length() == 4) {
					entry2[4] = "0" + entry2[4];
				}
				final String time1 = entry1[4];
				final String time2 = entry2[4];
				return time1.compareTo(time2);
			}
		});
	}

	// set the initial configuration
	public boolean setConfiguration(String[] paths) {
		System.out.println("### Configuració inicial###" + "\nDirectori per defecte: " + DIR);
		System.out.println("\nEn quin directori es troba el fitxer artistes.csv? "
				+ "\nEnter per agafar el directori per defecte");
		paths[0] = readDirectory();
		System.out.println("\nEn quin directori voldràs guardar les llistes de grups? "
				+ "\nEnter per agafar el directori per defecte");
		paths[1] = readDirectory();
		System.out.println("\nEn quin directori voldràs guardar la copia de seguretat? "
				+ "\nEnter per agafar el directori per defecte");
		paths[2] = readDirectory();
		System.out.println("\nMode d'escriptura dels fitxers que es generin: " + "\n0-Sobreescriptura"
				+ "\n1-Afegir a final de fitxer");
		int in = readNumber();
		if (in == 0) {
			System.out.println("Has triat sobreescriure.\n");
			return  false;			
		} else {
			System.out.println("Has triat annexar.\n");
			return  true;
		}
	}

	// method for organize all the content from the initial file to different files
	public void generateFiles(String[][] fileContent, String outputFileDir,Boolean append) {
		sortArray(fileContent);
		System.out.println("Fitxers generats:");
		// escriure linies a fitxer segons el seu estil
		// for every stage
		for (byte x = 0; x < STYLES.length; x++) {
			String stringToFile = "";
			// fo every line in the initial file
			for (byte line = 1; line < fileContent.length; line++) {
				// for every style of the stage
				for (byte c = 1; c < STYLES[x].length; c++) {
					// has the same style as the stage?
					if (fileContent[line][COLUMN_OF_STYLE].equals(STYLES[x][c])) {
						// append data to the string for the output file
						for (byte y = 0; y < (FILE_COLUMNS - 1); y++) {
							stringToFile += fileContent[line][y] + ",";
						}
						stringToFile += fileContent[line][FILE_COLUMNS - 1] + "\n";
					}
				}
			}
			// save information to a file
			writeToFile(stringToFile, outputFileDir + "/" + STYLES[x][0] + EXTENSION,append);
		}
	}

	// method to create a file from a String text
	public boolean writeToFile(String content, String fileRoute,Boolean append) {
		System.out.println(fileRoute);
		try (FileWriter fw = new FileWriter(fileRoute, append);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.print(content);
			out.close();
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	// method for cheking is the file exists
	public boolean isAFile(String path) {
		try {
			File f = new File(path);
			if (f.isFile()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {

		}
		return false;
	}

	// from csv file to a two dimensional array
	public String[][] getFileData(String inputFileDir) {
		String[][] error = { { "null" }, { "null" } };
		if (isAFile(inputFileDir)) {
			System.out.println("Llegint desde " + inputFileDir);
			try {
				File myFile = new File(inputFileDir);
				Scanner reader = new Scanner(myFile);
				List<String[]> list = new ArrayList<String[]>();
				while (reader.hasNextLine()) {
					list.add(reader.nextLine().split(","));
				}
				String[][] fileContent = new String[list.size()][FILE_COLUMNS];
				int index = 0;
				for (String[] linesOfList : list) {
					fileContent[index++] = linesOfList;
				}
				reader.close();
				return fileContent;
			} catch (Exception e) {
				System.out.println("Hi ha hagut un error en el proces de lectura");
				return error;
			}
		} else {
			System.out.println("\n!! No existeix el fitxer d'origen !!\n");
			return error;
		}
	}

	// if reads c returns the current directory
	public String readDirectory() {
		try {
			String dir = readString();
			File d = new File(dir);
			if (d.isDirectory()) {
				return dir;
			} else {
				System.out.println("Directori invàlid. Es farà servir el directori per defecte");
			}
		} catch (Exception e) {
		}
		return DIR;
	}

	public String readString() {
		Scanner read = new Scanner(System.in);
		return read.nextLine();
	}

	public int readNumber() {
		Scanner read = new Scanner(System.in);
		do {
			if (read.hasNextInt()) {
				return read.nextInt();
			} else {
				System.out.println("Error, escriu un num. ");
				read.next();
			}
		} while (true);
	}

}
