package ex.io;

import java.io.*;

/**
 * 练习4：编码实验——故意乱码
 *
 * 分三步验证"编码解码规则一致才不会乱码"：
 *   1. 用 OutputStreamWriter + GBK 写入中文
 *   2. 用 InputStreamReader + GBK 读回来打印（正常）
 *   3. 用 InputStreamReader + UTF-8 读回来打印（乱码）
 *
 * TODO: 补全 main 方法
 */
public class Ex04EncodingTest {
    public static void main(String[] args) throws Exception {
        String path = "D:\\Idea\\io\\2.txt";

        // TODO 1: 用 GBK 写入一段中文
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(path),"gbk");
        osw.write("你好");
        osw.close();
        // TODO 2: 用 GBK 读取并打印（应该正常）
        InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "gbk");
        isr.read();
        // TODO 3: 用 UTF-8 读取并打印（观察乱码）
        InputStreamReader isr1 = new InputStreamReader(new FileInputStream(path), "utf-8");
        isr1.read();

        isr.close();
        isr1.close();
    }
}
