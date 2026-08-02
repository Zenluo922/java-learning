package excise_network_upload;

import java.io.*;
import java.net.Socket;

/**
 * 练习三：TCP 文件上传 — 客户端
 *
 * 要求：
 * 1. 创建 Socket 对象，连接 127.0.0.1:9999
 * 2. 用 FileInputStream 读取本地文件（路径自己指定）
 * 3. 通过 OutputStream 将文件数据发送给服务端（边读边写）
 * 4. 发送完毕后调用 socket.shutdownOutput() 通知服务端
 * 5. 通过 InputStream 读取服务端的响应结果并打印
 * 6. 关闭所有资源
 *
 * 关键理解：
 * - 为什么要调 shutdownOutput()？
 *   → 服务端的 read() 只有读到 -1 才会停止，但 -1 只有在
 *     对方关闭了输出流时才会返回。如果客户端不调用 shutdownOutput()，
 *     服务端就会一直阻塞在 read()，等着接收更多数据！
 *
 * 提示：
 * - FileInputStream 读本地文件：while((len = fis.read(bytes)) != -1)
 * - OutputStream 写网络：os.write(bytes, 0, len)
 * - socket.shutdownOutput() 放在文件读完之后、读响应之前
 * - 客户端会自动创建测试文件（excise/src/excise_network_upload/test.txt）
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // TODO: 1. 创建 Socket 对象，连接 127.0.0.1:9999
        Socket socket = new Socket("127.0.0.1", 9999);

        // TODO: 2. 确定要上传的文件路径，创建 FileInputStream
        //      （可以先检查文件是否存在，不存在就创建一个带示例内容的）
        FileInputStream fis = new FileInputStream("D:\\Idea\\io\\1.jpg");
        // TODO: 3. 获取 OutputStream
        OutputStream os = socket.getOutputStream();
        // TODO: 4. 边读边写：从 fis 读 → 往 os 写
        //        //      while((len = fis.read(bytes)) != -1) { ... }
        byte[] bytes = new byte[1024];
        int len;
        while ((len=fis.read(bytes))!=-1){
            os.write(bytes,0,len);
        }
        // TODO: 5. 【重要】调用 shutdownOutput() 告诉服务端发完了
        socket.shutdownOutput();
        // TODO: 6. 获取 InputStream，读取服务端的响应
        InputStream is = socket.getInputStream();
        byte[] bytes1 = new byte[1024];
        int len1 = is.read(bytes1);
        String s = new String(bytes1, 0, len1);
        // TODO: 7. 打印服务端响应
        System.out.println(s);
        // TODO: 8. 关闭资源：is、os、fis、socket
        is.close();
        os.close();
        fis.close();
        socket.close();
    }
}
