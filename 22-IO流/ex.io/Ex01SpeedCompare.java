package ex.io;

import java.io.*;
/*
 * 练习1：文件复制效率对比
 *
 * 选一个比较大的文件（视频/图片，几十MB以上），分别用：
 *   方式A：FileInputStream + FileOutputStream，一次读写一个字节
 *   方式B：BufferedInputStream + BufferedOutputStream，一次读写一个字节
 * 复制到新文件，打印各自耗时（毫秒），感受缓冲流的效率提升。
 *
 * TODO: 补全 methodA() 和 methodB()，并在 main 中调用对比
 */
public class Ex01SpeedCompare {
    public static void main(String[] args) throws Exception {
        String src = "D:\\Idea\\io\\src.avi";   // TODO: 改成你电脑上存在的文件路径
        String destA = "D:\\Idea\\io\\copy_basic.avi";
        String destB = "D:\\Idea\\io\\copy_buffered.avi";
        methodA(src,destA);
        methodB(src,destB);
        // TODO: 调用 methodA 和 methodB，分别打印耗时
    }

    // TODO: 用基本字节流复制文件，返回耗时(ms)
    public static long methodA(String src, String dest) throws Exception {
        // 你的代码
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(dest);
        int len;
        while ((len = fis.read())!=-1){
            fos.write(len);
        }
        long end = System.currentTimeMillis();
        long l = start-end;
        System.out.println("l = " + l);
        fis.close();
        fos.close();
        return l;
    }

    // TODO: 用字节缓冲流复制文件，返回耗时(ms)
    public static long methodB(String src, String dest) throws Exception {
        // 你的代码
        long start = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
        int len;
        while ((len = bis.read())!= -1){
            bos.write(len);
        }
        long end = System.currentTimeMillis();
        long l = start - end;
        System.out.println("l = " + l);
        bis.close();
        bos.close();
        return l;
    }
}
