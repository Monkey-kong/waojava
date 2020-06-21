package unit15_javaio.c6;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

// 未成功
public class WriteToProcess {

	public static void main(String[] args) throws IOException {
		Process p = Runtime.getRuntime().exec("java D:\\01_code\\01_javasebasic\\waojava\\bin\\unit15_javaio\\c6\\ReadStandard");
		try(
			// 以 p 进程的输出流创建 PrintStream
			// 这个 ps 对本程序是输出流,对p进程是输入流
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
		System.out.println("============>main");
		try(
			Scanner sc = new Scanner(System.in);
			PrintStream ps = new PrintStream(new FileOutputStream("out.txt")))
		{
			sc.useDelimiter("\n");
			while(sc.hasNext())
			{
				ps.println("键盘输入的内容是：" + sc.next());
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}
