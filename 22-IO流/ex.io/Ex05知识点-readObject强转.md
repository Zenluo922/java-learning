# readObject() 为什么要强转？

---

## 问题

```java
ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
ArrayList<Hero> list = (ArrayList<Hero>) ois.readObject();
//                      ↑ 为什么必须强转？不加就报错！
```

## 原因

`readObject()` 的返回值类型是 `Object`——Java 中所有类的祖宗。

```
int read()        → 返回 int      （明确）
void write(int b) → 不用接收      （明确）
Object readObject() → 返回 Object （模糊！编译器不知道具体类型）
```

写入的时候类型信息不会被保留到文件里，编译器眼里文件内容就是一堆二进制。所以读回来时，编译器只敢说"这是个 `Object`"，不敢说具体是什么。

## 类比

```
你买了一个快递（writeObject → 文件）
快递到了拆出来是个盒子（readObject → Object）
盒子外面没写里面是啥（编译器不知道具体类型）
但你知道自己买了鞋（你知道存的是 ArrayList<Hero>）
所以你得自己拆盒确认（强转）
拆错了？炸（ClassCastException）
```

## 强转的作用

告诉编译器："别怕，这东西真实类型是 `ArrayList<Hero>`，按这个来"

```java
// 不转 → 编译报错
Object obj = ois.readObject();
obj.get(0);   // ❌ Object 没有 get 方法！

// 转了 → 能正常用
ArrayList<Hero> list = (ArrayList<Hero>) ois.readObject();
list.get(0);  // ✅ ArrayList 有 get 方法
Hero h = list.get(0);
h.getName();  // ✅ Hero 有 getName 方法
```

## 总结

| 问题 | 答案 |
|------|------|
| 为啥要强转？ | `readObject()` 返回 `Object`，不转没法用 |
| 谁决定的？ | 方法签名写死了返回 `Object` |
| 转错了会怎样？ | `ClassCastException` |
| 怎么知道该转成什么？ | 你写进去的是什么，读回来就转什么 |
