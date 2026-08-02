package excise_network_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 练习二：TCP 服务端
 *
 * 要求：
 * 1. 创建 ServerSocket 对象，绑定端口 9999
 * 2. 调用 accept() 等待客户端连接（阻塞方法）
 * 3. 打印客户端 IP 地址（socket.getInetAddress()）
 * 4. 获取 InputStream（接收）和 OutputStream（发送）
 * 5. 循环：
 *    - 读取客户端发来的消息
 *    - 用 StringBuilder 将消息反转
 *    - 把反转后的字符串返回给客户端
 *    - 收到 "886" 时退出
 * 6. 关闭所有资源
 *
 * 提示：
 * - ServerSocket.accept() 返回一个 Socket 对象
 * - new StringBuilder(字符串).reverse().toString() 反转字符串
 * - 注意关流顺序：先开后关（os → is → socket → serverSocket）
 */
public class Server {
    public static void main(String[] args) throws Exception {
        // TODO: 1. 创建 ServerSocket 对象，绑定端口 9999
        ServerSocket ss = new ServerSocket(9999);
        // TODO: 2. 打印启动提示
        System.out.println("开始启动");
        // TODO: 3. 调用 accept() 等待客户端连接
        Socket socket = ss.accept();
        // TODO: 4. 打印客户端 IP
        System.out.println(socket.getInetAddress());
        // TODO: 5. 获取 InputStream 和 OutputStream
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        // TODO: 6. 循环接收和响应
        byte[] bytes = new byte[1024];

        System.out.println();
        while (true){
            int len = is.read(bytes);
            String s = new String(bytes, 0, len);
            System.out.println(s);
            String s1 = new StringBuilder(s).reverse().toString();
            os.write(s1.getBytes());
            if(s.equals("886")){
                break;
            }
        }
            // 6.1 读取客户端消息（read → new String）
            // 6.2 打印收到的消息
            // 6.3 反转字符串（StringBuilder.reverse()）
            // 6.4 返回给客户端（getBytes → write）
            // 6.5 如果是 "886" 就 break
        // TODO: 7. 关闭资源：os、is、socket、ss
        os.close();
        is.close();
        socket.close();
        ss.close();
    }
}
