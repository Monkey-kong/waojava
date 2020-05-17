package unit15_javaio.c9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		try(
			@SuppressWarnings("resource")
			FileChannel channel = new FileOutputStream("a.txt").getChannel())
		{
			// 使用非阻塞的方式对指定文件加锁
			FileLock lock = channel.tryLock();
			Thread.sleep(10000);
			lock.release();
		}
	}
}
