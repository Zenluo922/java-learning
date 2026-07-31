package ex.io;

import java.io.*;

/**
 * 练习2：文本行号处理器
 *
 * 用 BufferedReader 读取一个文本文件，给每一行前面加上 "行号: "，
 * 然后用 BufferedWriter 写出到新文件。
 * 写完后在文件末尾用 newLine() 追加一行 "--- 共 X 行 ---"（X 是实际行数）。
 *
 * 提示：readLine() 读到文件末尾返回 null
 *
 * TODO: 补全 main 方法
 */
public class Ex02LineNumber {
    public static void main(String[] args) throws Exception {
        String src = "D:\\Idea\\io\\poem.txt";        // TODO: 改成你电脑上存在的文件
        String dest = "D:\\Idea\\io\\poem_numbered.txt";

        // TODO: 用 BufferedReader 逐行读取，加上行号后 BufferedWriter 写出
    }
}
