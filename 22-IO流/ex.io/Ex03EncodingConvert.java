package ex.io;

import java.io.*;

/**
 * 练习3：编码转换工具
 *
 * 假设有一个 GBK 编码的文本文件，
 * 用 InputStreamReader(GBK) 读取 → OutputStreamWriter(UTF-8) 写出，
 * 实现 GBK → UTF-8 的转码。
 *
 * TODO: 补全 main 方法
 */
public class Ex03EncodingConvert {
    public static void main(String[] args) throws Exception {
        String src = "D:\\Idea\\io\\1.txt";   // TODO: GBK 编码的源文件
        String dest = "D:\\Idea\\io\\2.txt";  // 转码后输出

        // TODO: 用 GBK 读，用 UTF-8 写
        InputStreamReader isr =
                new InputStreamReader(new FileInputStream(src), "gbk");
        OutputStreamWriter osw =
                new OutputStreamWriter(new FileOutputStream(dest), "utf-8");
        char[] chars= new char[1024];
        int len;
        while ((len=isr.read(chars))!=-1){
            osw.write(chars,0,len);
        }
        isr.close();
        osw.close();

    }
}
