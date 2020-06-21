package unit15_javaio.c6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromProcess {
	public static void main(String[] args) throws IOException, InterruptedException {
		Process p = Runtime.getRuntime().exec("javac");
		p.waitFor();
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk")))
		{

			String buff = null;
			while((buff = br.readLine()) != null)
			{
				System.out.println(buff);
			}
		}
	}
}
