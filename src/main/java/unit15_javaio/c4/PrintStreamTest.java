package unit15_javaio.c4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest 
{
	public static void main(String[] args) 
	{
		try(
			// 节点流
			FileOutputStream fos = new FileOutputStream("text.txt");
			// 包装为处理流
			PrintStream ps = new PrintStream(fos))
		{
			ps.println("普通字符串");
			ps.println(new PrintStreamTest());
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
