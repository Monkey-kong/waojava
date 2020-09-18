package unit15_javaio.c8.objstream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author Alvin.Li
 * 使用对象流反序列
 */
public class ReadObject {
	public static void main(String[] args) {
		// 创建对象流
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/main/java/unit15_javaio/c8/objstream/object.txt"))) {
			// 反序列化
			Person p = (Person)ois.readObject();
			System.out.println("名字为：" + p.getName() + "\n年龄为：" + p.getAge() + "\n身高为：" + p.getHeight());

			Person p1 = (Person)ois.readObject();
			System.out.println("名字为：" + p1.getName() + "\n年龄为：" + p1.getAge() + "\n身高为：" + p1.getHeight());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
