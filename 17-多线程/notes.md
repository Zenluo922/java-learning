# 模块 17：多线程 — 笔记

> 对应练习：`Deadlock/`、`Synchronized/`、`Excise_Pool/`

---

## 一、synchronized 售票（`Synchronized/`）

### 代码结构

- `Ticket` 实现 `Runnable`，`static int ticket = 100` 保证三个窗口共享 100 张票
- `synchronized (Ticket.class)` 锁类对象，同一时刻只有一个线程能卖票
- `while (flag)` 让线程循环卖票，`flag = false` 时 run 结束线程退出

### 关键理解

```java
Thread t1 = new Thread(ticket, "窗口1");
// 第一个参数 ticket 是 Runnable 对象
// 第二个参数 "窗口1" 是线程名字
// 三个线程共用同一个 ticket，所以票不会超卖
```

### 为什么 while 不用 if

线程的 `run()` 执行完就终止。用 `while(flag)` 循环执行，线程会反复卖票直到票卖完；用 `if(flag)` 只执行一次就结束了，没法循环卖 100 张。

### synchronized 两种写法

```java
// 方式 1：同步代码块
synchronized (Ticket.class) {
    // 卖票逻辑
}

// 方式 2：静态同步方法（效果一样）
public static synchronized void sellTicket() {
    // 卖票逻辑
}
```

---

## 二、死锁（`Deadlock/`）

### 产生条件

```
线程一：先拿锁 A → 再拿锁 B
线程二：先拿锁 B → 再拿锁 A
→ 线程一拿着 A 等 B，线程二拿着 B 等 A，互相等，卡死
```

### 你的代码对应

```java
// 线程一
synchronized (LockA.lockA) {   // 拿到 A
    sleep(100);
    synchronized (LockB.lockB) { // 等 B → 但 B 被线程二拿着
    }
}

// 线程二
synchronized (LockB.lockB) {   // 拿到 B
    sleep(100);
    synchronized (LockA.lockA) { // 等 A → 但 A 被线程一拿着
    }
}
// 两个线程互相等待 → 死锁
```

### 避免方式

**加锁顺序一致**：两个线程都先 A 后 B，就不会死锁。

---

## 三、线程池（`Excise_Pool/`）

```java
ExecutorService es = Executors.newFixedThreadPool(2);  // 固定 2 个线程
Future<Integer> s1 = es.submit(new MySum());           // submit 提交任务
Future<String> s2 = es.submit(new MyString());
System.out.println(s1.get());  // get() 阻塞获取返回结果
System.out.println(s2.get());
es.shutdown();                 // 记得关闭
```

### 不用线程池 vs 用线程池

| | new Thread | 线程池 |
|------|-----------|--------|
| 线程复用 | 每次新建，用完就扔 | 线程复用 |
| 资源控制 | 无限制，可能 OOM | 固定数量，可控 |
| 有返回值 | ❌ | ✅ Future |
