/*Homework 2 - G47
 * 
 * Sude Nur Çevik / 270201041
 * Melih Çakmak / 270201048
 * 
 * 
 * */



import java.io.FileNotFoundException;
import java.util.ArrayList;

public class VisaControlApplication {
	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<String> lineStrings = ApplicationManager.printableOutput();
		for (String string : lineStrings) {
			System.out.println(string);
		}
	

	}
}
