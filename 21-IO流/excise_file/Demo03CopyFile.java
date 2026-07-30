package excise_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo01CopyFile {
    public static void main(String[] args) throws IOException /* throws Exception */ {
        // 1.创建FileInputStream,指定要读取的图片路径
        FileInputStream fis = new FileInputStream("D:\\Idea\\io\\1.jpg");
        // 2.创建FileOutputStream,指定复制后的图片输出路径
        FileOutputStream fos = new FileOutputStream("D:\\Idea\\io\\zenluo.jpg");
        // 3.定义一个byte数组,长度为1024（或1024的倍数）
        byte [] bytes = new byte[1024];
        // 4.边读边写
        //    定义变量len接收每次读取的字节个数
        //    while循环: (len = fis.read(bytes)) != -1
        //        fos.write(bytes, 0, len);  // 读多少个,写多少个
        int len;
        while ((len = fis.read(bytes))!=-1){
            fos.write(bytes,0,len);
        }
        fos.close();
        fis.close();
        // 5.关流 —— 先开后关
        //    fos.close();
        //    fis.close();
    }
}
