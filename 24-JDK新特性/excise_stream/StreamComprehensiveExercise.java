package excise_stream;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

/**
 * Stream 流综合大练习 — 把 Lambda + 四大函数式接口 + Stream 全部串起来
 *
 * 知识点串联逻辑：
 *   Stream 方法的参数几乎全是函数式接口
 *   → forEach(Consumer)、filter(Predicate)、map(Function)、collect(Collector/Supplier)
 *   → 函数式接口的匿名内部类都能用 Lambda 简化
 *   → 所以：Stream + Lambda = Java8 最常用的组合拳
 */
public class StreamComprehensiveExercise {

    // ====================================================================
    // 综合题1：队伍筛选合并（经典面试套路）
    // ====================================================================
    //
    // 有两个队伍 List：
    //   队伍一：["迪丽热巴","宋远桥","苏星河","老子","庄子","孙子","洪七公"]
    //   队伍二：["古力娜扎","张无忌","张三丰","赵丽颖","张二狗","张天爱","张三"]
    //
    // 要求用 Stream 流一条链完成以下操作：
    //   ① 队伍一只要名字为 3 个字的成员（filter）
    //   ② 队伍一筛选之后只要前 3 个人（limit）
    //   ③ 队伍二只要姓"张"的成员（filter）
    //   ④ 队伍二筛选之后跳过前 2 个人（skip）
    //   ⑤ 将两个队伍合并为一个队伍（concat）
    //   ⑥ 打印整个队伍的姓名信息（forEach）
    //
    // 提示：
    //   整个操作可以一条链写完：
    //   Stream.concat(teamA.filter(...).limit(...), teamB.filter(...).skip(...)).forEach(...)
    //
    // ====================================================================

    public static void question1() {
        ArrayList<String> one = new ArrayList<>();
        // TODO: 给队伍一添加元素 ↓↓↓
        Stream<String> stream = Stream.of("迪丽热巴", "宋远桥", "苏星河", "老子", "庄子", "孙子", "洪七公");

        ArrayList<String> two = new ArrayList<>();
        // TODO: 给队伍二添加元素 ↓↓↓
        Stream<String> stream1 = Stream.of("古力娜扎", "张无忌", "张三丰", "赵丽颖", "张二狗", "张天爱", "张三");

        // TODO: 用 Stream 流一条链完成筛选合并打印 ↓↓↓
        Stream.concat(stream.filter(s-> s.length() == 3).limit(3),stream1.filter(s -> s.contains("张")).skip(2)).forEach(s -> System.out.println(s));
    }


    // ====================================================================
    // 综合题2：数字处理流水线
    // ====================================================================
    //
    // 有一个整数集合：[5, 12, 3, 9, 7, 8, 15, 2, 10]
    //
    // 要求用 Stream 流完成：
    //   a) 过滤出大于 5 的元素（filter）
    //   b) 从小到大排序（sorted — 提示：stream.sorted() 默认自然排序）
    //   c) 跳过第一个（最小的那个被跳过）
    //   d) 把每个元素转成 "数值:xxx" 的字符串（map）
    //   e) 收集到 List 中（collect）
    //   f) 打印最终结果
    //
    // 思考：sorted 排序后，跳过第一个，相当于去掉了什么？
    //
    // ====================================================================

    public static void question2() {
        // TODO: 在这里写综合题2代码 ↓↓↓
        ArrayList<Integer> list = new ArrayList<>();
        boolean addAll = Collections.addAll(list, 5, 12, 3, 9, 7, 8, 15, 2, 10);
        Stream<Integer> stream = list.stream();
        stream.filter(integer->integer>5).sorted().skip(1).map(integer-> "数值:"+integer).collect(Collectors.toList()).
                forEach(s -> System.out.println(s));

    }
    // ====================================================================
    // 综合题3：自定义对象 Stream 操作 — Person 去重 + 过滤 + 映射
    // ====================================================================
    //
    // Person 类已定义在下方，有 name 和 age 两个属性
    //
    // 要求：
    //   a) 用 Stream.of() 创建 Person 流，添加 5 个 Person：
    //      ("张三",18), ("李四",25), ("张三",18),  // 注意：这个和张三完全重复
    //      ("王五",30), ("赵六",22)
    //
    //   b) 对 Person 流进行以下操作：
    //      ① distinct 去重（需要 Person 重写 hashCode 和 equals，按 name+age 判定重复）
    //      ② filter 过滤出年龄 >= 20 的人
    //      ③ map 把 Person 转成字符串 "姓名:xxx, 年龄:xxx"
    //      ④ forEach 打印
    //
    // 提示：
    //   Person 去重需要重写 equals 和 hashCode（可以用 IDEA 自动生成）
    //
    // ====================================================================

    // TODO: 完善 Person 类 — 重写 equals 和 hashCode 方法 ↓↓↓
    public static class Person {
        private String name;
        private int age;

        public Person() {}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }

        // TODO: 重写 equals 方法（按 name + age 判定是否相同）

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return age == person.age && Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }


        // TODO: 重写 hashCode 方法（用 name + age 生成哈希值）


    }

    public static void question3() {
        // TODO: 在这里写综合题3代码 ↓↓↓
        Stream.of(new Person("张三", 18),
        new Person("李四",25),
        new Person("张三", 18),
        new Person("王五",30),
        new Person("赵六",22)).distinct().filter(person ->person.getAge()>=20).
                map(person ->"姓名:"+person.getName()+"年龄:"+person.getAge()).forEach(s -> System.out.println(s));
    }


    // ====================================================================
    // 综合题4：函数式接口 + Stream 的终极串联
    // ====================================================================
    //
    // 要求：自己写一个方法 processList，接收三个参数：
    //   List<String> list — 原始数据
    //   Predicate<String> predicate — 过滤条件
    //   Function<String, String> mapper — 转换规则
    //
    // 返回值：处理后的 List<String>
    
    // 方法内部用 Stream 流完成：
    //   list.stream().filter(predicate).map(mapper).collect(Collectors.toList())
    //
    // 然后在 main 中调用这个方法三次：
    //   ① 过滤出长度 >= 3 的名字，转成大写 → [JOHN, JANE, MIKE]
    //   ② 过滤出以"A"开头的名字，给每个名字加前缀 "VIP:" → ["VIP:Alice", "VIP:Alex"]
    //   ③ 过滤出包含"e"的名字，转成 "Hello, xxx" → ["Hello, Jane", "Hello, Alex"]
    //
    // 数据：["John","Jane","Alice","Mike","Alex","Bob"]
    //
    // 这题体现了"把行为当参数传递"的函数式编程思想精髓！
    //
    // ====================================================================

    // TODO: 在这里写 processList 方法 ↓↓↓
    public static List<String> processList
    (List<String> list, Predicate<String> predicate, Function<String, String> mapper){
        return list.stream().filter(predicate).map(mapper).collect(Collectors.toList());
    }
    public static void question4() {
        // TODO: 在这里写综合题4代码 ↓↓↓
        ArrayList<String> list = new ArrayList<>();
        list.add("John");
        list.add("Jane");
        list.add("Alice");
        list.add("Alex");
        list.add("Bob");
        List<String> re1 = processList(list, s -> s.length() >= 3, s -> s.toUpperCase());
        System.out.println("re1 = " + re1);
        List<String> re2 = processList(list, s -> s.startsWith("A"), s -> "VIP:" + s);
        System.out.println("re2 = " + re2);
        List<String> re3 = processList(list, s -> s.contains("e"), s -> "Hello." + s);
        System.out.println("re3 = " + re3);
    }


    // ========== 验证：写完上面的代码，去掉下面注释运行即可 ==========
    public static void main(String[] args) {
        System.out.println("========== 综合题1：队伍筛选合并 ==========");
        question1();
        System.out.println("========== 综合题2：数字处理流水线 ==========");
        question2();
        System.out.println("========== 综合题3：Person 去重 + 过滤 + 映射 ==========");
        question3();
        System.out.println("========== 综合题4：函数式接口 + Stream 终极串联 ==========");
        question4();
    }
}
