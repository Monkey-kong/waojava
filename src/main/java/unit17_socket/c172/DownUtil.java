package unit17_socket.c172;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author alvin
 * @date 2020-05-01 10:43
 */
public class DownUtil {

    // 定义下载资源的路劲(远程资源)
    private String path;
    // 指定下载文件保存位置(本地保存位置)
    private String targetFile;
    // 下载线程数
    private int threadNum;
    // 定义下载的线程对象
    private DownThread[] threads;
    // 定义下载的文件的总大小
    private int fileSize;

    public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        threads = new DownThread[threadNum];
    }

    public void download() throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept","image/gif, image/jepg, image/pjpeg, "
            + "application/x-shockwave-flash, application/xaml+xml");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        // 得到文件大小
        fileSize = conn.getContentLength();
        conn.disconnect();
        int currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        // 设置本地文件大小
        file.setLength(fileSize);
        file.close();
        for (int i = 0; i < threadNum; i++) {
            // 计算每个线程下载的开始位置
            int startPos = i * currentPartSize;
            // 每个线程使用一个 RandomAccessFile 进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
            // 定位该线程的下载位置
            currentPart.seek(startPos);
            // 创建下载线程
            threads[i] = new DownThread(startPos, currentPartSize, currentPart);
            // 启动下载线程
            threads[i].start();
        }
    }

    // 获取下载的完成百分比
    public double getCompleteRate() {
        // 统计多个线程已经下载的总大小
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += threads[i].length;
        }
        // 返回已经完成百分比
        return sumSize * 1.0 /fileSize;
    }

    private class DownThread extends Thread {
        // 当前线程的下载位置
        private int startPos;
        // 当前线程负责下载的文件大小
        private int currentPartSize;
        // 当前线程需要下载的文件块
        private RandomAccessFile currentPart;
        // 当前线程已经下载的字节数
        public int length;
        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept","image/gif, image/jepg, image/pjpeg, "
                        + "application/x-shockwave-flash, application/xaml+xml");
                conn.setRequestProperty("Accept-Language", "zh-CN");
                conn.setRequestProperty("Charset", "UTF-8");
                InputStream inStream = conn.getInputStream();
                // 跳过 startPos 个字节，表明该线程只下载自己负责的那部分文件
                inStream.skip(this.startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                // 读取网络数据，并写入本地文件
                while (length < currentPartSize
                    && (hasRead = inStream.read(buffer)) != -1) {
                    currentPart.write(buffer, 0, hasRead);
                    // 累计该线程下载的总大小
                    length += hasRead;
                }
                currentPart.close();
                inStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
