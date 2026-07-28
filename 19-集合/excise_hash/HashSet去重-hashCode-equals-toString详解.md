# HashSet 存储去重 + hashCode + equals + toString 全解析

## 1. 为什么输出有中括号？

`[ ]` 和逗号是 **HashSet（集合）的 `toString()` 自己加的**，跟元素无关。

调用链：

```
System.out.println(set)
  ↓
set.toString()  → AbstractCollection.toString()
  ↓  遍历每个元素，用 [ ] 包起来，逗号分隔
  ↓
遍历时每个元素调自己的 toString()
  ↓ Person 没重写 toString() → Object.toString()
  ↓
  → "类名@哈希码"  ← 这才是地址值！
```

图解：

```
[com.atguigu.e_hash.Person@6ce253f1, com.atguigu.e_hash.Person@53d8d10a]
│                                    │                                    │
└─ HashSet 的 "["              Person 地址值                   HashSet 的 "]"
```

---

## 2. 为什么输出是地址值（乱码）？

**因为 Person 没重写 `toString()`！**

```java
// Object 的 toString() 默认实现：
public String toString() {
    return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
// 结果：com.atguigu.e_hash.Person@6ce253f1
```

**解决：重写 toString()**

```java
@Override
public String toString() {
    return "Person{name='" + name + "', age=" + age + "}";
}
// 结果：Person{name='张无忌', age=19}
```

---

## 3. 为什么 HashSet 去重失败（两个"张无忌,19"都存进去了）？

因为 **Person 没重写 `hashCode()` 和 `equals()`**！

Object 的默认实现是**按内存地址**来的：

```java
Person p1 = new Person("张无忌", 19);  // 地址 0x100
Person p2 = new Person("张无忌", 19);  // 地址 0x200

p1.hashCode()  // 按地址 0x100 算 → 不一样！
p2.hashCode()  // 按地址 0x200 算 → 不一样！
// 两个哈希值不同 → 分到不同桶 → 不会比 equals → 直接存！
```

---

## 4. HashSet 去重的完整流程

```
add(元素)
  ↓
① 算元素的 hashCode()
  ↓ 桶里没人 → 直接存 ✅
  ↓ 桶里有人 → 进入 ②
② 比 equals()
  ↓ false（内容不同）→ 存 ✅（哈希碰撞）
  ↓ true （内容相同）→ 去重 ❌
```

一句话：**先看 hashCode 分房间，hashCode 撞了再比 equals 认人。**

---

## 5. 标准写法（IDEA 一键生成 Alt + Insert）

```java
import java.util.Objects;

public class Person {
    private String name;
    private Integer age;

    // ... 构造方法、getter/setter ...

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```

---

## 6. 总结

| 方法 | 作用 | 不重写的后果 |
|------|------|-------------|
| `toString()` | 打印可读内容 | 输出 `类名@6ce253f1` 乱码 |
| `hashCode()` | 计算哈希值，定位桶 | 两个相同内容的对象分到不同桶 |
| `equals()` | 判断内容是否相同 | 哈希碰撞后无法正确去重 |

**三个方法一个都不能少！存入 HashSet/HashMap 的自定义类必须重写 hashCode + equals！**
