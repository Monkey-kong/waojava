package unit15_javaio.c5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Alvin.Li
 */
public class RedirectIn {
	public static void main(String[] args) {
		try (FileInputStream fis = new FileInputStream("./src/main/java/unit15_javaio/c5/RedirectIn.java")) {
			System.setIn(fis);
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			sc.useDelimiter("\n");
			while(sc.hasNext()) {
				System.out.println("键盘输入的内容是：" + sc.next());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
