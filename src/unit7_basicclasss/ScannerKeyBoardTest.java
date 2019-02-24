package unit7_basicclasss;

import java.util.Scanner;

public class ScannerKeyBoardTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		while (sc.hasNext()) {
			System.out.println("键盘输入的内容是：" + sc.next());
		}
	}

}
