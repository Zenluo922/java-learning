# Java 字符串拼接：`+` 的自动类型转换

## 核心规则

> **字符串跟任何东西用 `+` 连接，结果都会变成字符串！**

## 示例

```java
"" + 2    →  "2"       // 空串 + int → String
"" + 10   →  "10"      // 空串 + int → String
"♠" + 2   →  "♠2"      // String + int → String
"a" + 1   →  "a1"      // String + int → String
```

## 为什么？

Java 规定：**`+` 的两边只要有一个是 String，整个结果就是 String**，另一边会被自动调用 `toString()` 转换。

## 常见用法

### 1. 快速把 int 转成 String
```java
int i = 5;
String s = "" + i;   // "5"
```

### 2. 拼接花色和牌号（斗地主案例）
```java
"♠" + 2  →  "♠2"
"♥" + "A" → "♥A"
```

## 为什么不能直接往 `ArrayList<String>` 里塞 int？

```java
ArrayList<String> number = new ArrayList<>();
number.add(2);        // ❌ 编译报错！int 不能转成 String
number.add("" + 2);   // ✅ "2" 是 String
number.add(String.valueOf(2));  // ✅ 另一种写法
```

## 其他转换方式

| 写法 | 说明 |
|------|------|
| `"" + i` | 最简短，练习常用 |
| `String.valueOf(i)` | 正规写法，推荐 |
| `Integer.toString(i)` | 效果同上 |

## 反过来：String 转 int

```java
int i = Integer.parseInt("123");  // "123" → 123
```

## 一句话总结

**`+` 两边只要有一个是 String，结果就是 String。`"" + 任何东西 = 字符串`。**
