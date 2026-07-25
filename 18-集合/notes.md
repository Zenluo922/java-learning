# 模块 18：集合 — 笔记

> 对应练习：`a_collection/`、`b_iterator/`、`c_list/`

---

## 一、Collection 接口（`a_collection/Demo1.java`）

| 方法 | 说明 |
|------|------|
| `add(E e)` | 添加元素，一定成功，不用 boolean 接收 |
| `addAll(Collection c)` | 合并另一个集合 |
| `remove(Object o)` | 删除指定元素 |
| `clear()` | 清空 |
| `contains(Object o)` | 是否包含 |
| `isEmpty()` | 是否为空 |
| `size()` | 元素个数 |
| `toArray()` | 转成数组 |

```java
Collection<String> c = new ArrayList<>();  // 接口接实现类
c.add("萧炎");
c.add("唐三");
System.out.println(c);  // [萧炎, 唐三]
```

---

## 二、迭代器（`b_iterator/`）

### 标准模板

```java
Iterator<Object> it = list.iterator();
while (it.hasNext()) {
    Object next = it.next();
    System.out.println(next);
}
```

**必须先 `hasNext()` 再 `next()`**，否则空集合直接抛 `NoSuchElementException`。

### 底层原理

`list.iterator()` 返回的是 `ArrayList` 的内部类 `Itr` 对象，它有两个关键变量：

| 变量 | 含义 | 初始值 |
|------|------|--------|
| `cursor` | 下一次 `next()` 返回的索引 | `0` |
| `lastRet` | 上一次 `next()` 返回的索引 | `-1`（-1 = "还没返回过"） |

调用一次 `next()`：
1. 取出 `cursor` 位置的元素
2. `lastRet = cursor`
3. `cursor++`

### 常见问题：ConcurrentModificationException

**Demo3 里写的 `listIterator.add()` 不会抛异常，但 `list.remove()` 会。**

原因：`Itr` 里还有一个 `expectedModCount`，每次 `next()` 会检查它和 `ArrayList.modCount` 是否一致。直接调 `list.remove()` 只改了 `modCount`，迭代器的 `expectedModCount` 没跟上，就炸了。

| 操作 | 是否安全 |
|------|----------|
| `iterator.remove()` | ✅ |
| `listIterator.add()` | ✅ |
| `list.remove()` | ❌ 抛 ConcurrentModificationException |
| `list.add()` | ❌ 同上 |

---

## 三、ArrayList（`c_list/`）

### 常用方法

| 方法 | 说明 |
|------|------|
| `add(E e)` | 尾部添加 |
| `add(int index, E e)` | 指定位置插入 |
| `remove(int index)` | 按索引删除，返回被删元素 |
| `remove(Object o)` | 按元素删除，返回 boolean |
| `set(int index, E e)` | 修改，返回被替换的旧元素 |
| `get(int index)` | 按索引获取 |
| `size()` | 元素个数 |

### 源码三要点

1. **懒加载**：`new ArrayList<>()` 时是空数组，第一次 `add()` 才创建容量 10 的数组
2. **自动扩容**：满了用 `Arrays.copyOf()` 复制到新数组
3. **扩容倍数**：约 **1.5 倍**（`oldCapacity + oldCapacity >> 1`）
