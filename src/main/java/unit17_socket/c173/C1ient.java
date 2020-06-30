package unit17_socket.c173;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author alvin
 * @date 2020-05-01 15:04
 */
public class C1ient {
    public static void main(String[] args) throws IOException, InterruptedException {
        final Socket socket = new Socket("127.0.0.1", 30000);
        socket.setSoTimeout(5000);

        // 将 Socket 对应的输入流包装成 BufferedReader
        final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final String line = br.readLine();
        System.out.println("服务器说：" + line);

        final PrintStream ps = new PrintStream(socket.getOutputStream());
        // Thread.sleep(6000); // 验证 SoTimeout，让 Client 超时
        ps.print("我是01号客户端，请求连接。");

        ps.close();
        br.close();
        socket.close();
    }
}
