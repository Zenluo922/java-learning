package excise_network_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * 练习一：UDP 发送端
 *
 * 要求：
 * 1. 创建 DatagramSocket 对象（空参构造，使用随机端口）
 * 2. 准备接收端的 IP 地址（127.0.0.1）和端口号（8888）
 * 3. 循环从控制台读取用户输入（用 Scanner）
 * 4. 将输入的字符串转成 byte[]，打包成 DatagramPacket 发送出去
 * 5. 输入 "886" 时退出循环
 * 6. 释放资源（socket、scanner）
 *
 * 提示：
 * - DatagramPacket 构造：new DatagramPacket(byte[] 数据, int 长度, InetAddress IP, int 端口)
 * - InetAddress.getByName("127.0.0.1") 获取 IP 对象
 * - socket.send(dp) 发送数据包
 */
public class Send {
    public static void main(String[] args) throws Exception {
        // TODO: 1. 创建 DatagramSocket 对象
        DatagramSocket socket = new DatagramSocket();

        // TODO: 2. 准备接收端的 IP 和端口
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 8888;
        // TODO: 3. 创建 Scanner 对象
        Scanner sc = new Scanner(System.in);

        // TODO: 4. 循环读取控制台输入并发送
        while (true){
            // 4.1 提示用户输入，读取一行
            // 4.2 将字符串转成 byte[]
            System.out.println("请输入");
            String n = sc.next();
            byte[]bytes = n.getBytes();
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, ip, port);
            socket.send(dp);
            System.out.println("已发送");
            if(n.equals("886") ){
                break;
            }
        }
        // 4.3 打包成 DatagramPacket
        // 4.4 发送
        // 4.5 打印"已发送：xxx"
        // 4.6 如果是 "886" 就 break
        socket.close();
        // TODO: 5. 释放资源
    }
}
