package unit7_basicclasss;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileTest {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("./src/unit7_basicclasss/ScannerFileTest.java"));
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
	}

}
