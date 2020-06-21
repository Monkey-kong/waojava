package unit15_javaio.c8;

import java.io.FileInputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;

/**
 * @author alvin
 * @date 2020-05-12 20:02
 */
public class FilterTest {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt")))
        {
            ois.setObjectInputFilter((info) -> {
                System.out.println("===执行数据过滤===");
                ObjectInputFilter serialFilter = ObjectInputFilter.Config.getSerialFilter();
                if (serialFilter != null) {
                    System.out.println("serialFilter is not null");
                    // 首先使用 ObjectInputFilter 执行默认的检查
                    ObjectInputFilter.Status status = serialFilter.checkInput(info);
                    // 如果默认检查的结果不是 Status.UNDECIDED
                    if (status != ObjectInputFilter.Status.UNDECIDED) {
                        System.out.println("serialFilter result is not equals to UNDECIDED.");
                        // 直接返回检查结果
                        return status;
                    }
                }
                // 如果要恢复的对象不是一个
                System.out.println(info.references());
                if (info.references() != 1) {
                    System.out.println("The object is not the only one.");
                    // 不允许恢复对象
                    return ObjectInputFilter.Status.REJECTED;
                }
                if (info.serialClass() != null &&
                    info.serialClass() != Person.class) {
                    System.out.println(info.serialClass());
                    System.out.println("The object is not the Person class.");
                    // 不允许恢复对象
                    return ObjectInputFilter.Status.REJECTED;
                }
                return ObjectInputFilter.Status.UNDECIDED;
            });
            // 从输入流中读取一个 Java 对象, 并强制转换为 Person 类
            Person p = (Person)ois.readObject();
            System.out.println("名字为:" + p.getName() +
                "\n年龄为:" + p.getAge());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
