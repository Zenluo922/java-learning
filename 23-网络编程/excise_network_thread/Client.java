package excise_network_thread;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 练习四：TCP 多线程 — 客户端
 *
 * 要求：
 * 1. 创建 Socket 对象，连接 127.0.0.1:9999
 * 2. 获取 OutputStream 和 InputStream
 * 3. 循环：从控制台读 → 发送 → 接收响应 → 打印
 * 4. 输入 "886" 时退出
 * 5. 关闭资源
 *
 * 说明：
 * 这个客户端和练习二的 TCP 客户端基本一样，
 * 区别在于服务端用了多线程，可以同时处理多个客户端。
 * 你可以同时启动多个本客户端来验证服务端的多线程能力！
 *
 * 提示：参考 excise_network_tcp/Client.java 的写法
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 1. 创建 Socket 对象，连接 127.0.0.1:9999
        Socket socket = new Socket("127.0.0.1", 9999);

        // 2. 获取 OutputStream 和 InputStream
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();

        // 3. 创建 Scanner
        Scanner sc = new Scanner(System.in);

        // 4. 循环通信
        while (true) {
            // 4.1 读取用户输入
            System.out.print("请输入消息（输入 886 退出）：");
            String msg = sc.nextLine();

            // 4.2 发送给服务端
            os.write(msg.getBytes());

            // 4.3 接收服务端响应
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            String response = new String(bytes, 0, len);

            // 4.4 打印响应
            System.out.println(response);

            // 4.5 如果是 "886" 就 break
            if (msg.equals("886")) {
                System.out.println("客户端已关闭~");
                break;
            }
        }

        // 5. 关闭资源
        is.close();
        os.close();
        socket.close();
        sc.close();
    }
}
