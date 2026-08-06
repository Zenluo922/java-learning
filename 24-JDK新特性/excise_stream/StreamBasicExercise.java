package excise_stream;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

/**
 * Stream 流基础方法练习
 *
 * 涵盖知识点：
 *   1. Stream 的获取：Collection.stream() / Stream.of(T...)
 *   2. 终结方法：forEach(Consumer) — 遍历 / count() — 统计 / collect(Collectors.toList()) — 转集合
 *   3. 中间方法（非终结，返回新 Stream）：
 *      filter(Predicate) — 过滤 / limit(n) — 取前n个 / skip(n) — 跳过前n个
 *      distinct() — 去重 / map(Function) — 类型转换 / concat() — 合并
 *
 * 复习串联：
 *   → 每个 Stream 方法的参数都是函数式接口（第一章 Lambda 的前提）
 *   → 所以 Stream + Lambda 是天作之合（第二章的四大接口全部用上）
 */
public class StreamBasicExercise {

    // ====================================================================
    // 题1：Stream 的获取 — 两种方式
    // ====================================================================
    // 要求：
    //   a) 创建一个 ArrayList<String>，add 三个名字，调用 list.stream() 获取流
    //   b) 直接用 Stream.of("A", "B", "C") 获取流
    //   c) 分别打印两个流对象（看看输出是啥）
    //
    // ====================================================================

    public static void question1() {
        // TODO: 在这里写题1代码 ↓↓↓
        ArrayList<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("赵敏");
        list.add("无忌");
        Stream<String> stream = list.stream();
        System.out.println(stream);
        Stream<String> stream1 = Stream.of("张无忌 ", "赵敏", "无忌");
        System.out.println(stream1);
    }


    // ====================================================================
    // 题2：forEach 遍历 + count 统计（终结方法）
    // ====================================================================
    // 要求：
    //   a) 用 Stream.of() 创建流："鲁班七号","妲己","安琪拉","亚瑟","后羿"
    //   b) 用 forEach 遍历打印每个元素
    //   c) 用 count() 统计元素个数并打印
    //
    // 注意：forEach 和 count 都是终结方法，用完流就关了！
    // 所以 b 和 c 不能共用同一个流对象，需要重新创建～
    //
    // ====================================================================

    public static void question2() {
        // TODO: 在这里写题2代码 ↓↓↓
        Stream<String> stream = Stream.of("鲁班七号", "妲己", "安琪拉", "亚瑟", "后羿");
        stream.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        Stream<String> stream1 = Stream.of("鲁班七号", "妲己", "安琪拉", "亚瑟", "后羿");
        long count = stream1.count();
        System.out.println("count = " + count);
    }


    // ====================================================================
    // 题3：filter 过滤 — 赛选"张"姓英雄
    // ====================================================================
    // 要求：
    //   a) 用 Stream.of() 创建流："张无忌","张三丰","张大彪","吕布","赵云","张飞","典韦"
    //   b) 用 filter 过滤出姓"张"的元素
    //   c) 用 forEach 打印过滤结果
    //
    // 提示：filter 的参数是 Predicate，Lambda 写法 s -> s.startsWith("张")
    //
    // ====================================================================

    public static void question3() {
        // TODO: 在这里写题3代码 ↓↓↓
        Stream<String> stream = Stream.of("张无忌", "张三丰", "张大彪", "吕布", "赵云", "张飞", "典韦");
        stream.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.startsWith("张");
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }


    // ====================================================================
    // 题4：limit + skip — 取前几个 / 跳过前几个
    // ====================================================================
    // 要求：
    //   a) 用 Stream.of() 创建流："A","B","C","D","E","F"
    //   b) 用 limit(3) 取前 3 个，打印结果 → 应该是 A B C
    //   c) 重新创建同样的流，用 skip(3) 跳过前 3 个，打印结果 → 应该是 D E F
    //
    // ====================================================================

    public static void question4() {
        // TODO: 在这里写题4代码 ↓↓↓
        Stream<String> stream = Stream.of("A", "B", "C", "D", "E", "F");
        stream.limit(3).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        System.out.println("==============");
        Stream<String> stream1 = Stream.of("A", "B", "C", "D", "E", "F");
        stream1.skip(3).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }


    // ====================================================================
    // 题5：distinct 去重
    // ====================================================================
    // 要求：
    //   a) 用 Stream.of() 创建流："张三","李四","张三","王五","李四","赵六"
    //   b) 用 distinct() 去重
    //   c) 用 forEach 打印去重后的结果
    //
    // 思考：String 为什么不用重写 hashCode 和 equals 就能去重？
    //
    // ====================================================================

    public static void question5() {
        // TODO: 在这里写题5代码 ↓↓↓
        Stream<String> stream = Stream.of("张三", "李四", "张三", "王五", "李四", "赵六");
        stream.distinct().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

    }
    // ====================================================================
    // 题6：map 类型转换 — 把整数流转成字符串流
    // ====================================================================
    // 要求：
    //   a) 用 Stream.of() 创建整数流：1, 2, 3, 4, 5
    //   b) 用 map 把每个整数转成字符串（"数字:" + i）
    //   c) 用 forEach 打印转换结果
    //
    // 提示：map 的参数是 Function<Integer, String>
    // 思考：打印 "数字:" + i 后，i 的类型是 Integer，它在这里被自动拆箱还是保持包装类型？
    //
    // ====================================================================

    public static void question6() {
        // TODO: 在这里写题6代码 ↓↓↓
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer+"";
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String o) {
                System.out.println(o+1);
            }
        });
    }


    // ====================================================================
    // 题7：concat 合并 + collect 转集合
    // ====================================================================
    // 要求：
    //   a) 用 Stream.of() 创建流1："张三","李四"
    //   b) 用 Stream.of() 创建流2："王五","赵六"
    //   c) 用 Stream.concat() 合并两个流
    //   d) 用 collect(Collectors.toList()) 把合并后的流转成 List
    //   e) 打印这个 List
    //
    // ====================================================================

    public static void question7() {
        // TODO: 在这里写题7代码 ↓↓↓
        Stream<String> stream = Stream.of("张三", "李四");
        Stream<String> stream1 = Stream.of("王五","赵六");
        Stream<String> concat = Stream.concat(stream, stream1);
        List<String> list = concat.collect(Collectors.toList());
        for (String s : list) {
            System.out.println(s);
        }
    }


    // ====================================================================
    // 题8：链式调用 — filter + map + forEach 一条龙
    // ====================================================================
    // 要求：
    //   用 Stream.of() 创建流："张三","李四","王五","张飞","赵六"
    //   一条链式调用完成：
    //     filter：只要姓"张"的
    //     map：在每个名字后面加上 "(张家人)"
    //     forEach：打印结果
    //
    // 最终输出应该是：
    //   张三(张家人)
    //   张飞(张家人)
    //
    // ====================================================================

    public static void question8() {
        // TODO: 在这里写题8代码 ↓↓↓
        Stream<String> stream = Stream.of("张三", "李四", "王五", "张飞", "赵六");
        stream.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("张");
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s+"(张家人)";
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }


    // ========== 验证：写完上面的代码，去掉下面注释运行即可 ==========
    public static void main(String[] args) {
        System.out.println("========== 题1：Stream 的获取 ==========");
        question1();
        System.out.println("========== 题2：forEach + count ==========");
        question2();
        System.out.println("========== 题3：filter 过滤 ==========");
        question3();
        System.out.println("========== 题4：limit + skip ==========");
        question4();
        System.out.println("========== 题5：distinct 去重 ==========");
        question5();
        System.out.println("========== 题6：map 类型转换 ==========");
        question6();
        System.out.println("========== 题7：concat + collect ==========");
        question7();
        System.out.println("========== 题8：链式调用一条龙 ==========");
        question8();
    }
}
