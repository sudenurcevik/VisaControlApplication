import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

	public static ArrayList<String[]> read() throws FileNotFoundException { // returns the lines of the given file.
		ArrayList<String[]> lines = new ArrayList<>();

		try {
			Scanner keyboardScanner = new Scanner(new File("HW2_ApplicantsInfo.csv"));
			String line;

			while (keyboardScanner.hasNextLine()) {

				line = keyboardScanner.nextLine();
				String[] argumentStrings = line.split(",");
				lines.add(argumentStrings);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return lines;

	}
}
