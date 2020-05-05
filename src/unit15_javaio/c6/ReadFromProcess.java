package unit15_javaio.c6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 未成功
public class ReadFromProcess {
	public static void main(String[] args) throws IOException{
		Process p = Runtime.getRuntime().exec("javac");
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream(),"gbk")))
		{
			String buff = null;
			while((buff = br.readLine()) != null)
			{
				System.out.println(buff);
			}
		}
	}
}
