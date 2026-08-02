package excise_network_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 练习二：TCP 客户端
 *
 * 要求：
 * 1. 创建 Socket 对象，连接 127.0.0.1:9999
 * 2. 获取 OutputStream（用于发送）和 InputStream（用于接收）
 * 3. 循环从控制台读取用户输入（Scanner）
 * 4. 通过 OutputStream 将用户输入发送给服务端
 * 5. 通过 InputStream 读取服务端的响应并打印
 * 6. 输入 "886" 时退出循环
 * 7. 关闭所有资源（先开后关原则）
 *
 * 提示：
 * - socket.getOutputStream() → 获取输出流
 * - socket.getInputStream() → 获取输入流
 * - os.write(字符串.getBytes()) → 把字符串转字节数组后发送
 * - is.read(byte[]) → 读取服务端响应，返回值是实际读取的字节数
 * - new String(byte[], 0, len) → 将字节数组转成字符串
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // TODO: 1. 创建 Socket 对象，连接 127.0.0.1:9999
        Socket socket = new Socket("127.0.0.1", 9999);
        // TODO: 2. 获取 OutputStream 和 InputStream
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        // TODO: 3. 创建 Scanner
        Scanner sc = new Scanner(System.in);
        // TODO: 4. 循环通信
            // 4.1 提示用户输入，读取一行
        while (true){
            System.out.println("请输入");
            String s = sc.nextLine();
            os.write(s.getBytes());
            byte[] bytes = new byte[1024];
            int len = is.read(bytes);
            String s1 = new String(bytes, 0, len);
            System.out.println(s1);
            if (s.equals("886")){
                break;
            }
        }
        socket.close();
        is.close();
        os.close();
            // 4.2 发送给服务端（字符串 → byte[] → write）
            // 4.3 接收服务端响应（read → new String）
            // 4.4 打印响应
            // 4.5 如果是 "886" 就 break



        // TODO: 5. 关闭资源：is、os、socket、sc

    }
}
