package excise_network_thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 练习四：TCP 多线程 — 服务端
 *
 * 要求：
 * 1. 创建 ServerSocket 对象，绑定端口 9999
 * 2. 用 while(true) 循环接收客户端连接
 * 3. 每 accept() 到一个客户端，就 new Thread 开启新线程处理
 * 4. 线程 run() 方法中：
 *    a. 获取 InputStream 和 OutputStream
 *    b. 循环读取客户端消息
 *    c. 在消息前加上 "[Server] 已收到: " 前缀
 *    d. 返回给客户端
 *    e. 收到 "886" 时退出循环
 * 5. 在 finally 块中关闭该客户端的资源（os、is、socket）
 * 6. 处理异常（try-catch）
 *
 * 核心理解：
 * - 为什么需要多线程？
 *   → 如果不用多线程，服务端一次只能服务一个客户端。
 *     第二个客户端连上来时，必须等第一个断开才能被处理。
 *     用了多线程后，每个客户端都有自己的线程，互不干扰。
 *
 * - accept() 和线程的关系？
 *   → 主线程负责 accept() 等新连接
 *   → 子线程负责和已连接的客户端通信
 *   → 两者同时进行，互不阻塞
 *
 * 提示：
 * - new Thread(Runnable).start() 开启新线程，别忘 .start()！
 * - finally 块中的关闭代码要各自 try-catch
 * - 子线程中用 Thread.currentThread().getName() 获取线程名
 */
public class Server {
    public static void main(String[] args) throws Exception {
        // TODO: 1. 创建 ServerSocket 对象，绑定端口 9999
        ServerSocket ss = new ServerSocket(9999);
        // TODO: 2. 打印启动提示
        System.out.println("启动");
        // TODO: 3. while(true) 循环接收客户端
        while (true){
            Socket socket = ss.accept();
            System.out.println("新客户端连接：" + socket.getInetAddress());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream is = null;
                    OutputStream os = null;
                    try {
                        is = socket.getInputStream();
                        os = socket.getOutputStream();
                        byte[] bytes = new byte[1024];
                        int len;
                        while ((len=is.read(bytes))!=-1){
                            String s = new String(bytes, 0, len);
                            System.out.println(s);
                            String s1 = "[Server] 已收到:" + s;
                            os.write(s1.getBytes());
                            if (s.equals("886")){
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            if (os != null) os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (is != null) is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (socket != null) socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

        }
            // 3.1 调用 accept() 等待新客户端
            // 3.2 打印新客户端 IP
            // 3.3 new Thread，重写 run() 方法
                // run() 内部：
                // a. 声明 is、os 为 null
                // b. try 块中：
                //    - 获取 is、os
                //    - 循环：读消息 → 打印 → 加前缀 → 回复 → 判断 886
                // c. catch 块：打印异常
                // d. finally 块：关闭 os、is、socket（各自 try-catch）
            // 3.4 调用 .start() 启动线程！千万别忘！

    }
}
