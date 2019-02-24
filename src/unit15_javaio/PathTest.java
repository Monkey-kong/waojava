package unit15_javaio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

	public static void main(String[] args) {
		Path path = Paths.get(".");
		System.out.println("path 里包含的路径数量：" + path.getNameCount());
		System.out.println("path 的根路径：" + path.getRoot());
		Path absolutePath = path.toAbsolutePath();
		System.out.println(absolutePath);
		System.out.println("absolutePath 的根路径：" + absolutePath.getRoot());
		System.out.println("absolutePath 里包含的路径数量：" + absolutePath.getNameCount());
		System.out.println(absolutePath.getName(1));
		Path path2 = Paths.get("g:", "publish", "codes");
		System.out.println(path2);
		
	}

}
