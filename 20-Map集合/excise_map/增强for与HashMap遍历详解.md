# 增强 for 循环 & HashMap 遍历

---

## 一、增强 for 语法

### 基本格式

```java
for (元素类型 变量名 : 被遍历的集合/数组) {
    // 循环体，用「变量名」取值
}
```

### 拆成三部分

```
for ( ①  :  ②  ) { ③ }
     类型 变量 来源
     
① 元素类型    → 集合里装的是什么类型就写什么
② 变量名      → 自己起名，每次循环拿到的元素存在这里
③ 来源        → 要被遍历的集合或数组
```

---

### 四组实战对照

```java
// ── 遍历数组 ──
int[] arr = {1, 2, 3};
for (int num : arr) {             // ①int  ②num  ③arr
    System.out.println(num);
}

// ── 遍历 ArrayList<Student> ──
ArrayList<Student> list = new ArrayList<>();
for (Student stu : list) {        // ①Student  ②stu  ③list
    System.out.println(stu.getName());
}

// ── 遍历嵌套 List（List 套 List）──
ArrayList<ArrayList<String>> big = new ArrayList<>();
for (ArrayList<String> small : big) {   // ①ArrayList<String> ②small ③big
    for (String s : small) {            // ①String  ②s  ③small
        System.out.println(s);
    }
}

// ── 遍历 HashMap 的 keySet ──
HashMap<Integer, String> map = new HashMap<>();
for (Integer key : map.keySet()) {      // ①Integer  ②key  ③map.keySet()
    System.out.println(map.get(key));
}
```

---

### 增强 for vs 普通 for

| | 普通 for | 增强 for |
|---|---|---|
| 语法 | `for (int i=0; i<len; i++)` | `for (类型 变量 : 集合)` |
| 有下标 | ✅ 可以拿到 `i` | ❌ 没有下标 |
| 修改元素 | ✅ `list.set(i, x)` | ❌ 不能改 |
| 简洁度 | 啰嗦 | 简洁 |

> **原则**：纯遍历用增强 for，需要下标（如发牌的 `i % 3`）用普通 for。

---

## 二、嵌套 List 遍历详解

### 数据视角

```java
ArrayList<String> list1 = new ArrayList<>();  // ["杨过", "小龙女", "尹志平"]
ArrayList<String> list2 = new ArrayList<>();  // ["涛哥", "金莲", "三上"]

ArrayList<ArrayList<String>> list = new ArrayList<>();  // 大盒子装小盒子
list.add(list1);
list.add(list2);
```

### 遍历时的类型推导

```
list（外层大集合）
  │  类型: ArrayList<ArrayList<String>>
  │  含义: 大盒子里装的是"小盒子"
  │
  └── for (ArrayList<String> small : list)  ← 每次取出小盒子
           │
           └── for (String s : small)        ← 从小盒子里取字符串
```

**关键规则**：来源是什么容器，就从中按元素类型一个一个掏。`list` 的元素是 `ArrayList<String>`，所以外层变量类型就是 `ArrayList<String>`；`small` 的元素是 `String`，所以内层变量类型是 `String`。

---

## 三、HashMap 三种遍历方式

HashMap 本身**不能直接用增强 for 遍历**，必须先转换。

```java
HashMap<Integer, String> map = new HashMap<>();
map.put(1, "张三");
map.put(2, "李四");
```

### 方式一：keySet() — 只拿 key

```java
for (Integer key : map.keySet()) {
    String value = map.get(key);   // 再手动查 value
}
// map.keySet() 返回 Set<Integer> → 可以 foreach
```

### 方式二：values() — 只拿 value

```java
for (String value : map.values()) {
    System.out.println(value);
}
// map.values() 返回 Collection<String> → 可以 foreach
// 缺点：拿不到 key
```

### 方式三：entrySet() — key + value 一起拿（推荐）

```java
Set<Map.Entry<Integer, String>> set = map.entrySet();
for (Map.Entry<Integer, String> entry : set) {
    Integer key   = entry.getKey();
    String  value = entry.getValue();
    // 一次同时拿到 key 和 value，不需要再回头查！
}
```

### 为什么推荐 entrySet？

```
方式1: 拿 key → map.get(key) → HashMap 内部又算一遍 hashCode → 多走一步
方式3: entry 里同时装着 key 和 value → 一次取出，不回头查
```

斗地主类比：

```
方式1 = 拿到牌编号，再翻字典查牌面（keySet）
方式3 = 编号和牌面一起打包给你（entrySet）
```

---

## 四、Map.Entry 是啥？

`Map.Entry` 就是 HashMap 内部存数据的**节点（Node）**，同时装着 key 和 value：

```java
Map.Entry<Integer, String> entry = ...
entry.getKey();    // → 1
entry.getValue();  // → "张三"

// 可以想象 entry 长这样：
// ┌──────────────┐
// │ key   = 1    │
// │ value = "张三" │
// └──────────────┘
```

---

## 五、HashMap 遍历全家福

```
HashMap (不能直接 foreach)
    │
    ├── map.keySet()    →  Set<Key>        →  for (Key k : ...)
    │                                       →  需要 map.get(k) 拿 value
    │
    ├── map.values()    →  Collection<Val>  →  for (Val v : ...)
    │                                       →  拿不到 key
    │
    └── map.entrySet()  →  Set<Entry>       →  for (Entry e : ...)
                                            →  e.getKey() + e.getValue()
                                            →  一次到位，性能最优 ✅
```
