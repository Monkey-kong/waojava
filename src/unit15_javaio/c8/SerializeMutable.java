package unit15_javaio.c8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author alvin
 * @date 2020-05-10 8:44
 */
public class SerializeMutable {
    public static void main(String[] args) {
        try(
            // 创建一个 ObjectOutputStream 输出流
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mutable.txt"));
            // 创建一个 ObjectInputStream 输入流
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mutable.txt"))
            )
        {
            Person per = new Person("孙悟空", 500);
            // 系统将 per 对象转换成字节序列并输出
            oos.writeObject(per);
            // 改变 per 对象的 name 实例变量值
            per.setName("周八戒");
            // 系统知识输出序列化编号,所以改变后的 name 不会被序列化
            oos.writeObject(per);
            Person p1 = (Person)ois.readObject();
            Person p2 = (Person)ois.readObject();
            // true
            System.out.println(p1 == p2);
            // 孙悟空
            System.out.println(p2.getName());
            System.out.println(p2.getAge());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
