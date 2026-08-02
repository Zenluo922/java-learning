package excise_network_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 练习一：UDP 接收端
 *
 * 要求：
 * 1. 创建 DatagramSocket 对象，绑定端口 8888
 * 2. 循环接收数据包
 * 3. 解析数据包：获取数据内容、发送端 IP、发送端端口
 * 4. 打印接收到的内容（格式："收到来自 xxx:xxx 的消息：xxx"）
 * 5. 收到 "886" 时退出循环
 * 6. 释放资源
 *
 * 提示：
 * - receive() 是阻塞方法，会一直等待直到收到数据
 * - dp.getData() → 获取数据（byte[]）
 * - dp.getLength() → 获取实际接收到的数据长度
 * - dp.getAddress() → 获取发送端的 IP
 * - dp.getPort() → 获取发送端的端口号
 * - new String(byte[] data, int offset, int length) 将字节数组转成字符串
 */
public class Receive {
    public static void main(String[] args) throws Exception {
        // TODO: 1. 创建 DatagramSocket 对象，绑定端口 8888
        DatagramSocket ds = new DatagramSocket(8888);

        // TODO: 2. 打印启动提示
        System.out.println("开始接收");

        // TODO: 3. 循环接收数据
        while (true){
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            ds.receive(dp);
            byte[] data = dp.getData();
            int port = dp.getPort();
            int len = dp.getLength();
            InetAddress address = dp.getAddress();
            String s = new String(bytes, 0,len);
            System.out.println(s);
            if (s.equals("886")){
                break;
            }
        }
            // 3.1 准备 byte[] 数组（长度 1024）和 DatagramPacket
            // 3.2 调用 receive() 接收数据包
            // 3.3 解析数据包：数据、长度、发送端 IP、发送端端口
            // 3.4 将 byte[] 转成字符串
            // 3.5 打印收到的消息
            // 3.6 如果是 "886" 就 break
        // TODO: 4. 释放资源
        ds.close();
    }
}
