package Excise_Pool;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
        int sum = 0;
        @Override
        public Integer call() throws Exception {
            for (int i = 0; i <= 100; i++) {
                sum += i;
            }
            return sum;
        }
    }

