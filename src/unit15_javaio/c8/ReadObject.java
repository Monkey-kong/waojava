package unit15_javaio.c8;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadObject {

	public static void main(String[] args) {
		try(
			// 创建对象流
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt")))
		{
			// 反序列化
			Person p = (Person)ois.readObject();
			System.out.println("名字为：" + p.getName() + "\n年龄为：" + p.getAge());

			Person p1 = (Person)ois.readObject();
			System.out.println("名字为：" + p1.getName() + "\n年龄为：" + p1.getAge());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
