package unit15_javaio.c8;

import unit15_javaio.c8.objstream.Person;

import java.io.Serializable;

/**
 * @author alvin
 * @date 2020-05-10 8:15
 */
public class SerialMachine implements Serializable {
    Person per = new Person("孙悟空", 500);
    Teacher t1 = new Teacher("唐僧", per);
    Teacher t2 = new Teacher("菩提老祖", per);
}
