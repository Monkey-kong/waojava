package unit15_javaio.c8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author alvin
 * @date 2020-05-10 8:25
 */
public class WriteTeacher {
    public static void main(String[] args) {
        try(
            // 创建一个 ObjectOutputStream 输出流
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt")))
        {
            Person per = new Person("孙悟空", 500);
            Teacher t1 = new Teacher("唐僧", per);
            Teacher t2 = new Teacher("菩提老祖", per);

            // 一次将四个对象写入输出流
            oos.writeObject(t1);
            oos.writeObject(t2);
            oos.writeObject(per);
            oos.writeObject(t2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
