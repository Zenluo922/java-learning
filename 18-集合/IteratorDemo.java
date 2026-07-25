import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器遍历 + ConcurrentModificationException 演示
 *
 * 知识点：
 * 1. list.iterator() 返回的是 ArrayList 内部类 Itr 的对象
 * 2. Itr 内部有 cursor（下一个索引）和 lastRet（上一个索引）
 * 3. 遍历时调用 list.remove() 会触发 ConcurrentModificationException
 *    因为 modCount 和 expectedModCount 不一致
 * 4. 正确做法：用 iterator.remove() 删除
 */
public class IteratorDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");

        // ===== 标准遍历模板 =====
        System.out.println("=== 标准遍历 ===");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // ===== 遍历时删除的正确写法 =====
        System.out.println("=== 删除李四 ===");
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            String name = it2.next();
            if ("李四".equals(name)) {
                it2.remove();   // ✅ 用迭代器的 remove
            }
        }
        System.out.println("删除后: " + list);

        // ===== 错误示范（取消注释会抛异常）=====
        // for (String name : list) {
        //     if ("张三".equals(name)) {
        //         list.remove(name);  // ❌ ConcurrentModificationException
        //     }
        // }
    }
}
