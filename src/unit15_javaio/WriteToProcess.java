package unit15_javaio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class WriteToProcess {

	public static void main(String[] args) throws IOException {
		Process p = Runtime.getRuntime().exec("java E:/1_workspace/workspace/waojava/bin/unit15_javaio/ReadStandard");
		try(
			PrintStream ps = new PrintStream(p.getOutputStream()))
		{
			ps.println("啊啊啊啊啊啊");
			ps.println(new WriteToProcess());
		}
	}
}

class ReadStandard
{
	public static void main(String[] args)
	{
		try(
			Scanner sc = new Scanner(System.in);
			PrintStream ps = new PrintStream(new FileOutputStream("out.txt")))
		{
			sc.useDelimiter("\n");
			while(sc.hasNext())
			{
				ps.print("键盘输入的内容是：" + sc.next());
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
