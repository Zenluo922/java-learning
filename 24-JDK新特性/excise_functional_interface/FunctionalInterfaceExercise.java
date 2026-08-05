package excise_functional_interface;

import java.util.*;
import java.util.function.*;

/**
 * 函数式接口小练习
 *
 * 涵盖知识点：
 *   1. Supplier<T>    — 供给型接口，T get() — 要什么给什么
 *   2. Consumer<T>    — 消费型接口，void accept(T t) — 操作数据
 *   3. Function<T,R>  — 转换型接口，R apply(T t) — T 转成 R
 *   4. Predicate<T>   — 判断型接口，boolean test(T t) — 判断条件
 */
public class FunctionalInterfaceExercise {

    // ====================================================================
    // 题1：Supplier 接口 — 求数组最大值
    // ====================================================================
    // 要求：
    //   a) 创建一个方法 getMax(Supplier<Integer> supplier)，返回最大值
    //   b) 在 main 中调用 getMax()，用 Lambda 传入一个数组 {5, 12, 3, 9, 7}，返回最大值
    //   c) 打印 "最大值是: xxx"
    //
    // 提示：
    //   Supplier<Integer> 的 get() 方法返回什么，我们就得到什么
    //   可以先排序，再取最后一个元素
    //   Arrays.sort(arr) 会改变原数组的顺序
    //
    // 参考格式：
    //   getMax(() -> {
    //       int[] arr = {5, 12, 3, 9, 7};
    //       Arrays.sort(arr);
    //       return arr[arr.length - 1];
    //   });
    // ====================================================================

    // TODO: 在这里写 getMax 方法 ↓↓↓
    public static void getMax(Supplier<Integer> supplier){
        Integer max = supplier.get();
        System.out.println("max = " + max);
    }
    // ====================================================================
    // 题2：Consumer 接口 — 格式化打印姓名
    // ====================================================================
    // 要求：
    //   a) 创建一个方法 printName(Consumer<String> consumer, String name)
    //   b) 在 main 中调用 printName()，用 Lambda 实现三种不同的消费方式：
    //      ① 直接打印名字
    //      ② 反转名字后打印（提示：new StringBuilder(name).reverse().toString()）
    //      ③ 打印名字的长度
    //
    // 提示：
    //   Consumer 的 accept(T t) 方法拿到数据后，想怎么操作就怎么操作
    //
    // 参考格式：
    //   printName(s -> System.out.println("姓名: " + s), "张三");
    // ====================================================================

    // TODO: 在这里写 printName 方法 ↓↓↓
    public static void printname(Consumer<String> consumer, String s){
        consumer.accept(s);
    }

    // ====================================================================
    // 题3：Function 接口 — 类型转换
    // ====================================================================
    // 要求：
    //   a) 创建一个方法 convert(Function<String, Integer> function, String str)
    //      将字符串转换成整数，并返回
    //   b) 在 main 中调用 convert()，用 Lambda 实现以下转换：
    //      ① 把字符串 "12345" 转成整数（提示：Integer.parseInt(s)）
    //      ② 把字符串 "hello" 转成它的长度（提示：s.length()）
    //      ③ 把字符串 "java" 转成它的 hashCode（提示：s.hashCode()）
    //   c) 打印每次转换的结果
    //
    // 提示：
    //   Function<T,R> — T 是输入类型，R 是返回值类型
    //   R apply(T t) — 根据 T 类型参数获取 R 类型结果
    //
    // 参考格式：
    //   convert(s -> Integer.parseInt(s), "12345");
    // ====================================================================

    // TODO: 在这里写 convert 方法 ↓↓↓
    public static void convert(Function<String,Integer> function,String str){
        Integer re = function.apply(str);
        System.out.println("re = " + re);
    }

    // ====================================================================
    // 题4：Predicate 接口 — 条件过滤
    // ====================================================================
    // 要求：
    //   a) 创建一个方法 filter(List<String> list, Predicate<String> predicate)
    //      遍历 list 中的每个元素，如果 predicate.test(s) 返回 true，就打印该元素
    //   b) 在 main 中创建一个 ArrayList<String>，添加多个人名
    //      用 addAll 添加："张三","张无忌","李四","张大彪","王五"
    //   c) 调用 filter() 三次，用 Lambda 实现不同的过滤条件：
    //      ① 打印所有姓"张"的人（提示：s.startsWith("张")）
    //      ② 打印所有名字长度为3的人（提示：s.length() == 3）
    //      ③ 打印所有名字中包含"大"的人（提示：s.contains("大")）
    //
    // 提示：
    //   Predicate 的 test(T t) 方法返回 boolean，用于判断
    //
    // 参考格式：
    //   filter(list, s -> s.startsWith("张"));
    // ====================================================================

    // TODO: 在这里写 filter 方法 ↓↓↓
    public static void filter(List<String> list, Predicate<String> predicate){
        for (String s : list) {
            if (predicate.test(s)){
                System.out.println("s = " + s);
            }
        }
    }

    // ========== 验证：写完上面的代码，去掉下面注释运行即可 ==========
    public static void main(String[] args) {
        System.out.println("========== 题1：Supplier 供给型 — 求最大值 ==========");
        // TODO: 调用 getMax() 并打印结果 ↓↓↓
        getMax(()-> {
                int[] arr = {4,3,4,6,7};
                Arrays.sort(arr);
                return arr[arr.length-1];
            }
        );

        System.out.println("========== 题2：Consumer 消费型 — 格式化打印 ==========");
        // TODO: 调用 printName() 实现三种消费方式 ↓↓↓
        printname(s1-> System.out.println(s1.length()),"abcdef");
        printname( s1->
                System.out.println(new StringBuilder(s1).reverse().toString())

        ,"abcdef");
        printname(  s1->
                System.out.println(s1)
        ,"abcdef");

        System.out.println("========== 题3：Function 转换型 — 类型转换 ==========");
        // TODO: 调用 convert() 实现三种转换 ↓↓↓
        convert(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        },"12345");
        convert(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.hashCode();
            }
        },"12345");
        convert(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        },"12345");

        System.out.println("========== 题4：Predicate 判断型 — 条件过滤 ==========");
        // TODO: 创建 list 并调用 filter() 实现三种过滤 ↓↓↓
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张三","张无忌","李四","张大彪","王五");
        filter(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("张");
            }
        });
        filter(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length()==3;
            }
        });
        filter(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("大");
            }
        });

    }
}
