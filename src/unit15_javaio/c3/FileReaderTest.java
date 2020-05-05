package unit15_javaio.c3;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
	public static void main(String[] args) throws IOException {
		// 自动关闭资源的 try 语句
		try(FileReader fr = new FileReader("./src/unit15_javaio/c3/FileReaderTest.java")) {
			char[] cbuf = new char[32];
			int hasRead = 0;
			while ((hasRead = fr.read(cbuf)) > 0) {
				System.out.print(new String(cbuf, 0, hasRead));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
