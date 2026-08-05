# 模块24 JDK新特性 — 知识点总结

---

## 第一章：Lambda 表达式

### 1. 函数式编程思想 vs 面向对象

| 面向对象 | 函数式编程 |
|---------|-----------|
| 强调**过程**：找对象 → 调方法 → 实现功能 | 强调**结果**：只关心有没有完成 |
| 例：去北京 — 怎么去（火车/飞机/骑车） | 例：去北京 — 去了还是没去 |

### 2. Lambda 定义格式

```java
() -> {}
```

| 部分 | 含义 |
|------|------|
| `()` | 重写方法的参数位置 |
| `->` | 将参数传递到方法体中 |
| `{}` | 重写方法的方法体 |

### 3. 使用前提

> **必须是函数式接口做方法参数传递！**

函数式接口 = 有且只有一个抽象方法的接口，用 `@FunctionalInterface` 检测。

### 4. 涛哥 Lambda 秘籍（5 步法）

```
① 观察是否是函数式接口做方法参数传递
② 如果是，考虑使用 Lambda 表达式
③ 调用方法，以匿名内部类的形式传递实参
④ 从 new 接口名 开始到 重写方法的方法名 结束 → 选中删除 + 删一个右大括号 }
⑤ 在参数后面、方法体大括号前面加上 ->
```

### 5. 省略规则（4 条）

| 规则 | 说明 | 示例 |
|------|------|------|
| 参数类型可省 | `(String s)` → `(s)` | `(s) -> {}` |
| 单参数小括号可省 | `(s)` → `s` | `s -> {}` |
| 单语句大括号+分号可省 | `{ xxx; }` → `xxx` | `s -> System.out.println(s)` |
| 单语句 return 可省 | `{ return xxx; }` → `xxx` | `(a,b) -> a - b` |

### 6. 经典对照

```java
// 匿名内部类（老写法）
new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("执行了");
    }
}).start();

// Lambda（新写法）
new Thread(() -> System.out.println("执行了")).start();
```

---

## 第二章：函数式接口

### 四大核心接口速查表

| 接口 | 抽象方法 | 作用 | 记忆口诀 |
|------|---------|------|---------|
| `Supplier<T>` | `T get()` | 供给型 — 无中生有 | **无进有出** — 要啥给啥 |
| `Consumer<T>` | `void accept(T t)` | 消费型 — 操作数据 | **有进无出** — 吃了就没了 |
| `Function<T,R>` | `R apply(T t)` | 转换型 — 类型转换 | **有进有出** — T 进 R 出 |
| `Predicate<T>` | `boolean test(T t)` | 判断型 — 条件判断 | **有进出布尔** — 判断是/否 |

### 1. Supplier\<T\> — 供给型

```java
// 场景：需要一个值，但不关心过程
Supplier<Integer> supplier = () -> {
    int[] arr = {4, 3, 4, 6, 7};
    Arrays.sort(arr);
    return arr[arr.length - 1];  // 返回最大值
};
Integer max = supplier.get();  // 7
```

### 2. Consumer\<T\> — 消费型

```java
// 场景：拿到数据后处理掉（打印/存储/发送...）
Consumer<String> consumer = s -> System.out.println(s.length());
consumer.accept("hello");  // 输出 5，无返回值
```

### 3. Function\<T, R\> — 转换型

```java
// 场景：把一种数据转成另一种
Function<String, Integer> func = s -> Integer.parseInt(s);
Integer num = func.apply("123");  // 123
```

### 4. Predicate\<T\> — 判断型

```java
// 场景：判断数据是否满足条件
Predicate<String> pred = s -> s.startsWith("张");
boolean result = pred.test("张三丰");  // true
```

### 泛型包装类对照表

> 泛型 `<>` 中只能写引用类型，不能写基本类型！

| 基本类型 | 包装类 |
|---------|--------|
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | **`Integer`** |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

---

## 第三章：Stream 流

### 核心概念

> Stream 流 ≠ IO 流，它是**流式编程**，像一条**流水线**。

### 方法分类总表

#### 获取流（起点）

| 方式 | 代码 |
|------|------|
| 从集合获取 | `list.stream()` |
| 从数组获取 | `Stream.of("A", "B", "C")` |

#### 中间方法（非终结，返回新 Stream）

| 方法 | 参数类型 | 作用 | 示例 |
|------|---------|------|------|
| `filter` | `Predicate` | 条件过滤 | `.filter(s -> s.length() > 2)` |
| `map` | `Function` | 类型转换 | `.map(s -> s.length())` |
| `limit` | `long` | 取前 N 个 | `.limit(3)` |
| `skip` | `long` | 跳过前 N 个 | `.skip(2)` |
| `distinct` | 无 | 去重（依赖 hashCode+equals） | `.distinct()` |
| `sorted` | `Comparator`(可选) | 排序 | `.sorted()` |

#### 终结方法（用完流就关！）

| 方法 | 参数类型 | 作用 | 示例 |
|------|---------|------|------|
| `forEach` | `Consumer` | 遍历 | `.forEach(System.out::println)` |
| `count` | 无 | 统计个数 | `.count()` → `long` |
| `collect` | `Collector` | 转集合 | `.collect(Collectors.toList())` |

#### 静态方法

| 方法 | 作用 |
|------|------|
| `Stream.concat(s1, s2)` | 合并两个流 |

### 经典链式调用

```java
list.stream()
    .filter(s -> s.startsWith("张"))   // Predicate — 过滤
    .filter(s -> s.length() == 3)      // Predicate — 再过滤
    .forEach(s -> System.out.println(s)); // Consumer — 遍历打印
```

### 常用模式

```java
// 过滤 → 转换 → 收集
List<String> result = Stream.of("a", "bb", "ccc")
    .filter(s -> s.length() >= 2)           // 过滤
    .map(s -> s.toUpperCase())              // 转换
    .collect(Collectors.toList());          // 收集

// 统计
long count = Stream.of(1, 2, 3, 4, 5)
    .filter(n -> n > 3)
    .count();  // 2

// 跳过 + 限制（分页）
stream.skip(2).limit(3)  // 跳过前2个，取3个 = 第3~5条
```

---

## 三章串联图

```
┌──────────────────────────────────────────────┐
│              Lambda 表达式（第一章）            │
│   () -> {}  省略规则  匿名内部类 → 一行搞定     │
└──────────────────┬───────────────────────────┘
                   │ 简化的是 →
                   ▼
┌──────────────────────────────────────────────┐
│           函数式接口（第二章）                  │
│  Supplier  Consumer  Function  Predicate     │
│  供给型    消费型    转换型    判断型          │
└──────────────────┬───────────────────────────┘
                   │ 作为参数传给 →
                   ▼
┌──────────────────────────────────────────────┐
│             Stream 流（第三章）                │
│  filter(Predicate)  map(Function)            │
│  forEach(Consumer)  collect(Supplier)        │
│  链式调用一条龙！                              │
└──────────────────────────────────────────────┘
```

> **核心心法**：Stream 的方法参数几乎全是函数式接口，函数式接口用 Lambda 简化，三者是天然的一家人！

---

## 练习题索引

| 文件 | 内容 |
|------|------|
| `excise_lambda/LambdaExercise.java` | Lambda 基础 3 题（线程、排序、省略规则） |
| `excise_functional_interface/FunctionalInterfaceExercise.java` | 四大接口 4 题 |
| `excise_stream/StreamBasicExercise.java` | Stream 方法 8 题 |
| `excise_stream/StreamComprehensiveExercise.java` | Stream 综合 5 题（含终极串联） |
