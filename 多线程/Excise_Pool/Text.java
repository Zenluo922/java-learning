package Excise_Pool;

import com.atguigu.e_pool.MyRunnable;
import com.atguigu.f_pool.MySum;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Text {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> s1 = es.submit(new MySum());
        Future<String> s2 = es.submit(new MyString());

        System.out.println(s1.get());
        System.out.println(s2.get());
    }
}
