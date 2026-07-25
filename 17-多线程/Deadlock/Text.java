package Excise_dieLock;

import static java.lang.Thread.sleep;

public class Text {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (LockA.lockA) {
                    System.out.println(Thread.currentThread().getName() + "抢到了锁A");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (LockB.lockB) {
                        System.out.println(Thread.currentThread().getName() + "抢到了锁B,A");
                    }
                }

            }
        },"线程一").start();
        new Thread(() -> {
            synchronized (LockB.lockB) {
                System.out.println(Thread.currentThread().getName() + "抢到了锁B");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (LockA.lockA) {
                    System.out.println(Thread.currentThread().getName() + "抢到了锁A,B");
                }
            }
        }, "线程二").start();
    }
    }

