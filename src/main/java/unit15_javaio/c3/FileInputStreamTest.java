package unit15_javaio.c3;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Alvin.Li
 */
public class FileInputStreamTest {
	public static void main(String[] args) throws IOException {
		String relativelyPath = System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(relativelyPath + "/src/main/java/unit15_javaio/c3/FileInputStreamTest.java");
		byte[] bbuf = new byte[1024];
		int hasRead = 0;
		while ((hasRead = fis.read(bbuf)) > 0) {
			System.out.print(new String(bbuf, 0, hasRead));
		}
		fis.close();
	}
}
