package unit15_javaio.c8;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	private /*transient*/ String name;
	private /*transient*/ int age;
	public Person(String name, int age)
	{
		System.out.println("有参构造器");
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
//	private void writeObject(ObjectOutputStream out) throws IOException
//	{
//		out.writeObject(new StringBuffer(name).reverse());
//		out.writeInt(age);
//	}
//
//	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException
//	{
//		this.name = ((StringBuffer)in.readObject()).reverse().toString();
//		this.age = in.readInt();
//	}
	
//	private Object writeReplace() 
//	{
//		ArrayList<Object> list = new ArrayList<Object>();
//		list.add(name);
//		list.add(age);
//		return list;
//	}
	
}
