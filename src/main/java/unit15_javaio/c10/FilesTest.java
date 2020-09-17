package unit15_javaio.c10;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilesTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Path p = Paths.get("./src/unit15_javaio/c10/FilesTest.java");
		Files.copy(p, new FileOutputStream("a.txt"));
		
		System.out.println(Files.isHidden(p));
		
		List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);
		System.out.println(lines);

		// 判断指定文件的大小
		System.out.println(Files.size(p));
		List<String> poem = new ArrayList<>();
		poem.add("莫愁前路无知己");
		poem.add("天下谁人不识君");
		Files.write(Paths.get("poem.txt"), poem, StandardCharsets.UTF_8);

		// 当前目录下的所有文件和子目录
		Files.list(Paths.get(".")).forEach(System.out::println);
		Files.lines(p, StandardCharsets.UTF_8).forEach(System.out::println);
		
		FileStore cStore = Files.getFileStore(Paths.get("C:"));
		System.out.println("c:共有空间：" + cStore.getTotalSpace());
		System.out.println("c:可用空间：" + cStore.getUsableSpace());
	}
}