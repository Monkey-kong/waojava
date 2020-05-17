package unit15_javaio.c8.custom;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author alvin
 * @date 2020-05-12 21:03
 */
public class CustomTest {
    public static void main(String[] args) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("custom.txt"));
             ObjectInputStream ois = new ObjectInputStream(
                     new FileInputStream("custom.txt"))) {
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
