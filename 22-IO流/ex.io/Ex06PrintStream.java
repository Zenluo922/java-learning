package ex.io;

import java.io.*;

/**
 * 练习6：第五章 打印流 PrintStream
 *
 * 知识点覆盖：
 *   1. PrintStream 构造与基本使用 —— print() / println()
 *   2. System.setOut() 改变输出流向 —— 控制台 → 文件（日志持久化）
 *   3. 续写 —— PrintStream(OutputStream out) 配合 FileOutputStream(path, true)
 *
 * 注意：PrintStream 永远不会抛 IOException，这点和其他流不一样！
 *
 * TODO: 补全所有标记处的代码
 */
public class Ex06PrintStream {
    public static void main(String[] args) throws Exception {
        String path = "D:\\Idea\\io\\log.txt";

        // ========== 基本使用：println vs print ==========
        // TODO 1: 创建 PrintStream 指向 path
        // TODO 2: 用 println 写几行（验证自带换行）
        // TODO 3: 用 print 连续写几个（验证不带换行，会粘在一起）
        // TODO 4: 关流
        PrintStream ps = new PrintStream(path);
        ps.println("你好");
        ps.println("好你");
        ps.println("不好");
        ps.print("哈喽");
        ps.print("喽");
        ps.close();



        // ========== 进阶：改变流向 + 续写 ==========
        // TODO 5: 创建支持续写的 PrintStream（FileOutputStream(path, true)）
        // TODO 6: 用 System.setOut() 改变流向
        // TODO 7: 写几句 System.out.println()，去文件里看看输出
        // TODO 8: 关流（用 System.err.println 在控制台提示"日志已写入文件"）
        PrintStream ps1 = new PrintStream(new FileOutputStream("ex.o\\log.txt", true));
        System.setOut(ps1);
        System.out.println("你好");
        ps1.close();
        System.err.println("日志已经写入文件");
        // 思考：
        // - System.out 本身是什么类型？
        // - 改变流向有什么实际用处？
    }
}
