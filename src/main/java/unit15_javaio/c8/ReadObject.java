package unit15_javaio.c8;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Alvin.Li
 * 使用对象流反序列
 */
public class ReadObject {
	public static void main(String[] args) {
		// 创建对象流
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			// 反序列化
			Person p = (Person)ois.readObject();
			System.out.println("名字为：" + p.getName() + "\n年龄为：" + p.getAge());

			Person p1 = (Person)ois.readObject();
			System.out.println("名字为：" + p1.getName() + "\n年龄为：" + p1.getAge());
//			Person p2 = (Person)ois.readObject();
//			System.out.println("名字为：" + p2.getName() + "\n年龄为：" + p2.getAge());
//			Person p3 = (Person)ois.readObject();
//			System.out.println("名字为：" + p3.getName() + "\n年龄为：" + p3.getAge());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
