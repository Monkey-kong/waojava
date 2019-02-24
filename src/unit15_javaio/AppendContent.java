package unit15_javaio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class AppendContent {

	public static void main(String[] args) {
		try(
			RandomAccessFile raf = new RandomAccessFile("out.txt", "rw"))
		{
			raf.seek(raf.length());
			raf.write("追加的内容\r\naaaaaaa".getBytes());
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
	}

}
