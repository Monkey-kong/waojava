package unit15_javaio.c8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {

	public static void main(String[] args) {
		try(
			// 创建对象流
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt")))
		{
			// 对象序列化
			Person per = new Person("孙悟空", 500);
			oos.writeObject(per);

			Person per1 = new Person("alvin", 500);
			oos.writeObject(per1);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
