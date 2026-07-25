package Excise_synchornized;

public class Text {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(true);

        Thread t1 = new Thread(ticket,"窗口1");
        Thread t2 = new Thread(ticket,"窗口2");
        Thread t3 = new Thread(ticket,"窗口3");//都传ticket是因为要共用100张ticket票

        t1.start();
        t2.start();
        t3.start();
    }
}/*题目：实现一个多线程卖票系统：
        3个窗口（线程）同时售卖 100 张票，票号为 1~100
        用两种方式实现线程安全：
        方式 1：同步代码块（锁类对象）
        方式 2：静态同步方法
        要求不出现超卖、重卖，票卖完后线程自动退出*/
    /*个人疑问与解答:
            1.run方法里为啥用 while
            解:线程的 run() 方法执行完就会终止。用 while(flag) 循环，只要 flag 为 true，
              线程就会一直循环执行 sellTicket() 卖票，不会执行一次就退出。
               如果用 if(flag)，线程只会执行一次 sellTicket() 就结束，没法循环卖 100 张票。

            2.为什么 else 后是 false 就代表结束线程
            解:当为false时，while (flag)变为false，run执行完后就结束结束线程

            3.测试类里 new Thread 传的第一个参数是什么？Thread t1 = new Thread (ticket, "窗口 1"); 这个 ticket 参数
            解：是开头创建的实例对象Ticket ticket = new Ticket(true);
                Thread 类的这个构造方法是：public Thread(Runnable target, String name)
                可以理解为创建一个线程 t1，让它执行 ticket 对象里的 run() 方法，并给这个线程起个名字叫‘窗口 1’”*/