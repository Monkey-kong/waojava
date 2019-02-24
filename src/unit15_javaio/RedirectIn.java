package unit15_javaio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class RedirectIn {

	public static void main(String[] args) {
		try(
			FileInputStream fis = new FileInputStream("./src/unit15_javaio/RedirectIn.java"))
		{
			System.setIn(fis);
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			sc.useDelimiter("\n");
			while(sc.hasNext())
			{
				System.out.print("键盘输入的内容是：" + sc.next());
			}
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
	}

}
