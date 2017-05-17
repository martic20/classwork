package introToFiles;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ReadFile1 {

	public static void main(String[] args) {
		ReadFile1 a = new ReadFile1();
		Charset encoding = Charset.defaultCharset();
		a.lectorBytes();

	}

	public void lectorBytes() {
		String url = "/home/" + System.getProperty("user.name") + "/Escriptori/";
		String fileType = ".txt";
		Scanner read = new Scanner(System.in);
		System.out.println("Nom del fitxer amb el que vols treballar:");
		String filename = read.nextLine();
		String filedir = url  + filename.split("[./]")[0] + fileType;
		while (!new File(filedir).isFile()) {
			System.out.println(filedir);
			System.out.println("El fitxer no existeix, escriu un nom diferent:");
			filename = read.nextLine();
			filename = filename.split("[./]")[0];
			filedir = url + filename + fileType;
		}
		try {
			RandomAccessFile file = new RandomAccessFile(filedir, "rw");
			byte[] b = {127,42,32,23};
			
			boolean fin = true;
			while(fin){
				byte n;
				if( (n = file.readByte())==-1){
					fin=false;
				}else{
					System.out.println(n);
				}
				
			}
			
			
			
		} catch (Exception e) {

		}

	}

	public void generadorNotes() {
		String url = "/home/" + System.getProperty("user.name") + "/Escriptori/";
		String fileType = ".txt";
		Scanner read = new Scanner(System.in);
		System.out.println("Nom del fitxer que conté les notes:");
		String filename = read.nextLine();
		String filedir = url + "/" + filename.split("[./]")[0] + fileType;
		String newfiledir = url + "Histograma-" + filename + fileType;
		while (!new File(filedir).isFile()) {
			System.out.println("El fitxer no existeix, escriu un nom diferent:");
			filename = read.nextLine();
			filename = filename.split("[./]")[0];
			filedir = url + filename + fileType;
		}
		try {
			File myFile = new File(filedir);
			Scanner fileReader = new Scanner(myFile);
			PrintStream writer = new PrintStream(url + "Histograma-" + filename + fileType);
			double in = 0;
			boolean fi = true;
			int[] notes = { 0, 0, 0, 0 };
			do {
				if (fileReader.hasNextDouble() || read.hasNextInt()) {
					in = fileReader.nextDouble();
					if (in == -1) {
						fi = false;
					} else {
						notes[converter(in)]++;
					}
				}
			} while (fi);
			writer.println("Exel·lent: " + some(notes[0]));
			writer.println("Notable  : " + some(notes[1]));
			writer.println("Aprovat  : " + some(notes[2]));
			writer.println("Suspès   : " + some(notes[3]));
			System.out.println("Fitxer generat. ");
		} catch (Exception e) {
		}
	}

	public String some(int i) {
		String ex = "";
		while (i > 0) {
			ex = ex + "*";
			i--;
		}
		return ex;
	}

	public int converter(double in) {
		if (in >= 9) {
			return 3;
		} else {
			if (in >= 6.5) {
				return 2;
			} else {
				if (in >= 5) {
					return 1;
				} else {
					return 0;
				}
			}
		}
	}

	public void readFile(Charset encoding) {
		try {
			InputStream myFile = new FileInputStream("/home/marti.casas/reals.myfile");
			Reader reader = new InputStreamReader(myFile, encoding);
			Reader buffer = new BufferedReader(reader);
			int r, count = 0, line = 1;
			while ((r = reader.read()) != -1) {
				if (r == 10) {
					System.out.println("Line " + (line++) + " length: " + count);
					count = 0;
				} else {
					count++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void readHTML(Charset encoding) {
		try {
			InputStream myFile = new FileInputStream("/home/marti.casas/mylist.html");
			Reader reader = new InputStreamReader(myFile, encoding);
			int r, treelevel = 0;
			boolean isTag = false, closetag = false, spaces = false;
			String text = "";
			System.out.println("HTML file:");
			while ((r = reader.read()) != -1) {

				switch (r) {
				case 10:// salt de linia
					break;
				case 32:// espai
					if (!spaces) {
						text += " ";
					}
					spaces = true;
					break;
				case 60:// <
					isTag = true;
					treelevel++;
					if (text.length() > 0) {
						System.out.print("  Value: " + text);
					}
					text = "";
					break;
				case 62:// >
					isTag = false;
					if (!closetag) {
						System.out.print(printLevel(treelevel) + "Tag: " + text);
					}
					closetag = false;
					text = "";
					break;
				case 47:// is /
					if (isTag) {
						treelevel--;
						treelevel--;
						closetag = true;
					}
					break;
				default:
					if (isTag) {
						text += (char) r;
					} else {
						text += (char) r;
					}
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String printLevel(int in) {
		String t = "\n";
		while (in > 1) {
			t += "| ";
			in--;
		}
		t += "|-";
		return t;
	}

	public void readString() {
		try {
			File myFile = new File("/home/marti.casas/reals.myfile");
			Scanner read = new Scanner(myFile);
			do {
				System.out.println(read.nextLine());
			} while (read.hasNextLine());
			read.close();
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public void getHigherNum() {
		int max = 0;
		try {
			File myFile = new File("/home/marti.casas/reals.myfile");
			Scanner read = new Scanner(myFile);
			while (read.hasNextLine()) {
				if (read.hasNextInt()) {
					int n = read.nextInt();
					if (n > max) {
						max = n;
					}
				}
				read.nextLine();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		System.out.println("THe best is " + max);
	}
}
