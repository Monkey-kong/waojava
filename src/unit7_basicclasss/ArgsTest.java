package unit7_basicclasss;

public class ArgsTest {

	public static void main(String[] args) {
		System.out.println("参数个数：" + args.length);
		for (String arg : args) {
			System.out.println(arg);
		}
	}

}
