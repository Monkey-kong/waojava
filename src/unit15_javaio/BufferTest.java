package unit15_javaio;

import java.nio.CharBuffer;

public class BufferTest {

	public static void main(String[] args) {
		// 创建 Buffer
		CharBuffer buff = CharBuffer.allocate(8);
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());
		
		// 放入元素
		buff.put('a');
		buff.put('b');
		buff.put('c');
		System.out.println("放入三个元素后：");
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());
		
		buff.flip();
		System.out.println("flip后：");
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

		System.out.println("第一个元素：" + buff.get());
		System.out.println("取出一个元素后：");
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

		buff.clear();
		System.out.println("clear后：");
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

		System.out.println("执行clear()后，缓冲区元素并没有被清除：例如还可以取到第三个元素：" + buff.get(2));
		
		System.out.println("执行绝对取数后：position 不变");
		System.out.println("capacity：" + buff.capacity() + "\n" + "limit：" + buff.limit() + "\n" + "position：" + buff.position());

	}

}
