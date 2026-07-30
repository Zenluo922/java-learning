package excise_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo05FileInputStream {
    public static void main(String[] args) throws IOException {
        // 调用 method02 测试一次读取一个字节数组
        method02();
    }

    /*
       int read(byte[] b)  一次读取一个字节数组,返回的是读取的字节个数
     */
    private static void method02() throws IOException /* throws Exception */ {
        // 1.创建FileInputStream对象,指定要读取的文件路径
        FileInputStream fis = new FileInputStream("C:\\Users\\zz\\Desktop\\Hello Claude\\Excise\\src\\excise_file\\1.txt");
        /*
            创建一个数组:byte[]
             1.创建的数组相当于一个临时存储区域,我们要读取的内容会临时保存到数组中
               然后我们再从数组中将数据获取

             2.数组长度定为多少,每次读取多少个,一般情况下数组长度定为1024或者1024的倍数
               如果剩下的字节不够数组长度了,那么就最后有多少读多少
         */
        // byte[] bytes = new byte[1024];
        byte[] bytes = new byte[2];
        /*
           读取过程演示（假设文件内容为 "abcde",数组长度为2）:
            第1次读取: fis.read(bytes) → 返回2 → 数组中存入 'a','b'
            第2次读取: fis.read(bytes) → 返回2 → 数组中存入 'c','d'
            第3次读取: fis.read(bytes) → 返回1 → 数组中存入 'e', 索引1位置保留上次的 'd'

            注意: 如果直接 new String(bytes) 不指定长度,第3次会输出 "ed"
                  所以需要用 new String(bytes, 0, len) 指定有效长度
         */
        // 2.定义一个变量len,接收每次读取的字节个数
        int len;
        while ((len = fis.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }

        // 3.while循环: (len = fis.read(bytes)) != -1 → 读到末尾返回-1,循环结束
        // 4.循环内: new String(bytes, 0, len) 将有效字节转为字符串输出
        // 5.关闭流
        fis.close();
    }
}
