package excise_network_upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * 练习三：TCP 文件上传 — 服务端
 *
 * 要求：
 * 1. 创建 ServerSocket 对象，绑定端口 9999
 * 2. 调用 accept() 等待客户端连接
 * 3. 获取 InputStream，读取客户端发来的文件数据
 * 4. 用 UUID.randomUUID() + 时间戳生成唯一文件名
 * 5. 创建 FileOutputStream，将数据保存到 excise/upload/ 目录
 * 6. 边读边写：从 is 读 → 往 fos 写（直到 read() 返回 -1）
 * 7. 通过 OutputStream 给客户端响应"上传成功！文件已保存为 xxx"
 * 8. 关闭所有资源
 *
 * 关键理解：
 * - 服务端怎么知道文件传完了？
 *   → 客户端调用了 shutdownOutput()，服务端的 read() 会返回 -1
 * - 为什么要用 UUID？
 *   → 多个客户端可能上传同名文件，UUID 保证文件名唯一
 *
 * 提示：
 * - UUID.randomUUID().toString() → 生成随机十六进制字符串
 * - System.currentTimeMillis() → 当前时间戳（毫秒）
 * - new File("excise/upload").mkdirs() → 确保目录存在
 */
public class Server {
    public static void main(String[] args) throws Exception {
        // TODO: 1. 创建 ServerSocket 对象，绑定端口 9999
        ServerSocket ss = new ServerSocket(9999);
        // TODO: 2. 打印启动提示，调用 accept() 等待连接
        System.out.println("启动");
        Socket socket = ss.accept();
        // TODO: 3. 获取 InputStream
        InputStream is = socket.getInputStream();
        // TODO: 4. 生成唯一文件名（UUID + 时间戳）
        String s = UUID.randomUUID().toString();
        String name = s + System.currentTimeMillis();
        // TODO: 5. 确保 upload 目录存在，创建 FileOutputStream
        FileOutputStream fos = new FileOutputStream("D:\\Idea\\io\\" + name + ".jpg");
        // TODO: 6. 边读边写：从 is 读 → 往 fos 写
        //      while((len = is.read(bytes)) != -1) { ... }
        byte[] bytes = new byte[1024];
        int len;
        while ((len = is.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        // TODO: 7. 获取 OutputStream，给客户端响应
        OutputStream os = socket.getOutputStream();
        os.write("上传成功".getBytes());
        // TODO: 8. 关闭资源：os、fos、is、socket、ss
        os.close();
        fos.close();
        is.close();
        socket.close();
        ss.close();
    }
}
