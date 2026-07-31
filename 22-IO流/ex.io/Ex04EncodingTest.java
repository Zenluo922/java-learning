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
        String path = "D:\\Idea\\io\\encoding_test.txt";

        // TODO 1: 用 GBK 写入一段中文

        // TODO 2: 用 GBK 读取并打印（应该正常）

        // TODO 3: 用 UTF-8 读取并打印（观察乱码）
    }
}
