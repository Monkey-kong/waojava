package unit15_javaio.c8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author alvin
 * @date 2020-05-12 20:40
 */
public class TransientTest {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("transient.txt"));
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("transient.txt"))) {
            Person per = new Person("Alvin", 26);
            oos.writeObject(per);
            Person p = (Person)ois.readObject();
            System.out.println(p.getName());
            System.out.println(p.getAge());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
